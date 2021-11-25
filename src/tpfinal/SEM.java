package tpfinal;

import java.util.List;
import java.util.Observable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class SEM extends Observable {

	private List<ZonaSem> zonasDeEstacionamiento;
	private List<Estacionamiento> estacionamientosEnCurso;
	private List<Compra> compras;
	private List<Infraccion> infracciones;
	private HashMap<Celular, Integer> saldos;
	private LocalTime horaActual;
	private HashMap<Celular, String> celularesEstacionados;

	public SEM() {
		this.compras = new ArrayList<Compra>();
		this.estacionamientosEnCurso = new ArrayList<Estacionamiento>();
		this.zonasDeEstacionamiento = new ArrayList<ZonaSem>();
		this.infracciones = new ArrayList<Infraccion>();
		this.saldos = new HashMap<Celular, Integer>();
		this.celularesEstacionados = new HashMap<Celular, String>();
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
		LocalTime finDeEstacionamiento = this.calcularFinalDeEstacionamiento(celular);
		if (finDeEstacionamiento.isBefore(hora) && this.noSonLasOcho()
				&& this.créditoSuficiente(celular, finDeEstacionamiento.compareTo(hora))) {
			EstacionamientoApp operación = new EstacionamientoApp(patente, celular);
			String info = "Su estacionamiento es valido desde las " + String.valueOf(hora) + "hs. " + "Hasta las "
					+ String.valueOf(finDeEstacionamiento) + "hs.";
			this.addEstacionamiento(operación);
			this.celularesEstacionados.put(celular, patente);
			return info;
		} else {

			return "Saldo Insuficiente. Estacionamiento no permitido";

		}

	}

	private boolean noSonLasOcho() {
		return this.horaActual.getHour() < 20;
	}

	/**
	 * 
	 * @param celular Celular a obtener el saldo
	 * @return El saldo del celular o 0 si el celular nunca cargo saldo y no esta en
	 *         el sistema
	 */
	public int consultarSaldo(Celular celular) {
		return this.saldos.getOrDefault(celular, 0);
	}

	/**
	 * 
	 * @param celular      De tipo Celular
	 * @param horaDeCompra de tipo int
	 * @return Un número que representa la hora de finalización de un
	 *         estacionamiento
	 */
	public LocalTime calcularFinalDeEstacionamiento(Celular celular) {
		int saldo = this.consultarSaldo(celular);
		LocalTime horas = this.horaActual;
		while (saldo >= 40) {
			horas = horas.plusHours(1);
			saldo = saldo - 40;
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

		// this.estacionamientosEnCurso.stream()
		// .filter(est -> est.getCelular() != null && est.getCelular()==
		// celular).toList().get(0);

		this.estacionamientosEnCurso.remove(estacionamiento);
		this.descontarCrédito(celular, this.consultarSaldo(celular), estacionamiento);

		String info = this.retornarInfo(estacionamiento.getHorarioInicio(), estacionamiento.getHorarioFin(),
				estacionamiento.duración(), this.consultarSaldo(celular));

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
	 */
	private void descontarCrédito(Celular celular, int monto, Estacionamiento est) {
		this.saldos.put(celular,
				this.consultarSaldo(celular) - (est.getHorarioInicio().compareTo(LocalTime.now()) * 40));
	}

	private boolean créditoSuficiente(Celular celular, int horas) {
		return this.consultarSaldo(celular) / 40 >= horas;
	}

	public void finalizarTodosLosEstacionamientos() {
		List<Estacionamiento> estacionamientos = this.estacionamientosEnCurso;
		LocalTime horaFin = LocalTime.of(20, 0);
		if (this.getHoraActual().isAfter(horaFin)) {
			this.estacionamientosEnCurso.removeAll(estacionamientos);
		}

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

	/**
	 * Añade el monto al saldo actual del celular, si no esta registrado en el
	 * sistema lo registra y carga el celular con ese monto
	 * 
	 * @param celular Key
	 * @param monto   Value
	 */
	public void cargarCrédito(Celular celular, int monto) {
		this.saldos.put(celular, monto + this.consultarSaldo(celular));
	}

	/*
	 * String.valueOf(estacionamiento.getHorarioInicio()) +
	 * String.valueOf(estacionamiento.getHorarioFin()) +
	 * String.valueOf(estacionamiento.duración()) +
	 * String.valueOf(estacionamiento.getCelular().getCrédito());
	 */

}
