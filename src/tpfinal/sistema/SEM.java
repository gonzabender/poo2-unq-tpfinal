package tpfinal.sistema;

import java.util.List;
import java.util.Observable;
import java.util.Set;
import java.util.stream.Stream;

import tpfinal.app.usuario.Celular;
import tpfinal.compras.Compra;
import tpfinal.compras.estacionamientos.Estacionamiento;
import tpfinal.compras.estacionamientos.EstacionamientoApp;
import tpfinal.inspector.Infraccion;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class SEM extends Observable {

	private List<ZonaSem> zonasDeEstacionamiento;
	private List<Estacionamiento> estacionamientosEnCurso;
	private List<Compra> compras;
	private List<Infraccion> infracciones;
	private LocalTime horaActual;
	private HashMap<Celular, String> celularesEstacionados;
	private int precioHora;

	public SEM() {
		this.compras = new ArrayList<Compra>();
		this.estacionamientosEnCurso = new ArrayList<Estacionamiento>();
		this.zonasDeEstacionamiento = new ArrayList<ZonaSem>();
		this.infracciones = new ArrayList<Infraccion>();
		this.celularesEstacionados = new HashMap<Celular, String>();
		this.precioHora = 40;
	}

	public void addZona(ZonaSem zona) {
		zonasDeEstacionamiento.add(zona);
	}

	public void addEstacionamiento(Estacionamiento estacionamiento) {
		estacionamientosEnCurso.add(estacionamiento);
		String info = "Estacionamiento iniciado a las " + String.valueOf(estacionamiento.getHorarioInicio())
				+ "hs. Y finalizado a las " + String.valueOf(estacionamiento.getHorarioFin()) + "hs.";
		this.setChanged();
		this.notifyObservers(info);
	}

	public void addInfraccion(Infraccion i) {
		this.infracciones.add(i);
	}

	public void addCompra(Compra compra) {
		this.compras.add(compra);
	}

	/**
	 * 
	 * @param celular El celular del cliente.
	 * @param patente La patente del cliente.
	 * @param hora    La hora de inicio del estacionamiento.
	 * @return Informacion de la hora de inicio y de fin, o un mensaje de error si
	 *         el celular no posee suficiente saldo.
	 */
	public String iniciarEstacionamiento(Celular celular, String patente, LocalTime hora) {
		if (this.noSonLasOcho()) {
			LocalTime finDeEstacionamiento = this.calcularFinalDeEstacionamiento(celular);
			EstacionamientoApp operación = new EstacionamientoApp(patente, celular);
			String info = "Su estacionamiento es valido desde las " + String.valueOf(hora) + "hs. " + "Hasta las "
					+ String.valueOf(finDeEstacionamiento) + "hs.";
			this.addEstacionamiento(operación);
			this.celularesEstacionados.put(celular, patente);
			return info;
		} else {

			return "No es requerido estacionar despues de las 20:00hs";

		}

	}

	private boolean noSonLasOcho() {
		return this.horaActual.getHour() < 20;
	}

	/**
	 * 
	 * @param celular      De tipo Celular
	 * @param horaDeCompra de tipo int
	 * @return Un número que representa la hora de finalización de un
	 *         estacionamiento
	 */
	public LocalTime calcularFinalDeEstacionamiento(Celular celular) {
		int saldo = celular.getSaldo();
		LocalTime horas = this.horaActual;
		while (saldo >= precioHora) {
			horas = horas.plusHours(1);
			saldo = saldo - precioHora;
		}
		return horas;
	}

	/**
	 * 
	 * @param celular El número del celular del cliente, lo unico requerido para
	 *                finalizar su estacionamiento.
	 * @return Información variada sobre el servicio otorgado
	 */
	public String finalizarEstacionamiento(Celular celular) {
		String patente = this.celularesEstacionados.get(celular);
		Estacionamiento estacionamiento = this.estacionamientosEnCurso.stream()
				.filter(est -> est.getPatente() == patente).findFirst().get(); // Si no estaciono esto falla, hay que
		// tocarlo para preguntar si estaciono antes
		estacionamiento.setHorarioFin(horaActual);
		
		this.estacionamientosEnCurso.remove(estacionamiento);
		this.descontarCrédito(celular, celular.getSaldo(), estacionamiento);

		String info = this.retornarInfo(estacionamiento.getHorarioInicio(), estacionamiento.getHorarioFin(),
				estacionamiento.duración(), celular.getSaldo());

		this.setChanged();
		this.notifyObservers(info);

		return info;
	}

	public String retornarInfo(LocalTime inicio, LocalTime fin, int duración, int crédito) {
		return "Hora de Inicio: " + String.valueOf(inicio) + "hs. Hora de fin: " + String.valueOf(fin)
				+ "hs. Duración: " + String.valueOf(duración) + "hs. Crédito restante: " + String.valueOf(crédito);
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public List<Estacionamiento> getEstacionamientosEnCurso() {
		return estacionamientosEnCurso;
	}

	/**
	 * Descuenta saldo al celular
	 * 
	 * @param celular Celular a descontar saldo
	 * @param monto   Monto a descontar
	 * @param est     Se necesita la hora de inicio para compararla con la hora
	 *                actual y asi descontar
	 */
	private void descontarCrédito(Celular celular, int monto, Estacionamiento est) {
		celular.restarSaldo(((LocalTime.now().getHour() - est.getHorarioInicio().getHour()) * this.precioHora));
	}

	// (LocalTime.now().getHour() - est.getHorarioInicio().getHour()) *40;

	private boolean créditoSuficiente(Celular celular, int horas) {
		return celular.getSaldo() / precioHora >= horas;
	}

	public void finalizarTodosLosEstacionamientos() {
		List<Estacionamiento> estacionamientos = this.estacionamientosEnCurso;
		if (!this.noSonLasOcho()) {
			for (Estacionamiento e : estacionamientos) {
				e.terminarEstacionamiento();
			}
			this.estacionamientosEnCurso.removeAll(estacionamientos);
		}

	}

	// public Celular celularDelEstacionamiento(Estacionamiento est) {
	// this.celularesEstacionados.keySet().stream().filter(cel ->
	// this.celularesEstacionados);

	// }

	public void descontarTodosLosCréditos(int monto, Estacionamiento est) {
		this.celularesEstacionados.keySet().forEach(cel -> this.descontarCrédito(cel, monto, est));
		;
	}

	public Stream<Infraccion> getInfraccionesVehiculo(String patente) {
		List<Infraccion> inf = this.getInfracciones();
		Stream<Infraccion> res = inf.stream().filter(i -> i.getPatente() == patente);
		return res;
	}

	public List<Infraccion> getInfracciones() {
		return infracciones;
	}

	public LocalTime getHoraActual() {
		return horaActual;
	}

	public void setHoraActual(LocalTime horaActual) {
		this.horaActual = horaActual;
	}

	
	public boolean tieneSaldoSuficiente(Celular cel) {
		return cel.getSaldo() >= this.precioHora;
	}



}
