package tpfinal;

import java.util.List;
import java.util.Observable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class SEM extends Observable {

	private List<ZonaSem> zonasDeEstacionamiento = new ArrayList<ZonaSem>();
	private List<Estacionamiento> estacionamientosEnCurso;
	private List<Compra> compras;
	private List<Infraccion> infracciones = new ArrayList<Infraccion>();
	private HashMap<Celular, Integer> saldos = new HashMap<Celular, Integer>();
	private LocalTime horaActual;

	public SEM() {
		this.estacionamientosEnCurso = new ArrayList<Estacionamiento>();
		this.compras = new ArrayList<Compra>();
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
	public String iniciarEstacionamiento(Celular celular, String patente, int hora) {
		int finDeEstacionamiento = this.calcularFinalDeEstacionamiento(celular, hora);
		if (finDeEstacionamiento > hora && hora < 20 && this.créditoSuficiente(celular, finDeEstacionamiento - hora)) {
			EstacionamientoApp operación = new EstacionamientoApp(patente, celular);
			String info = "Su estacionamiento es valido desde las " + String.valueOf(hora) + "hs. " + "Hasta las "
					+ String.valueOf(finDeEstacionamiento) + "hs.";
			this.addEstacionamiento(operación);

			return info;
		} else {

			return "Saldo Insuficiente. Estacionamiento no permitido";

		}

	}

	public int consultarSaldo(Celular celular) {
		return this.saldos.get(celular);
	}

	/**
	 * 
	 * @param celular      De tipo Celular
	 * @param horaDeCompra de tipo int
	 * @return Un número que representa la hora de finalización de un
	 *         estacionamiento
	 */
	public int calcularFinalDeEstacionamiento(Celular celular, int horaDeCompra) {
		int saldo = this.consultarSaldo(celular);
		int horas = horaDeCompra;
		while (saldo >= 40) {
			horas = horas + 1;
			saldo = saldo - 40;
		}
		return horas;
	}

	/**
	 * 
	 * @param número El número del celular del cliente, lo unico requerido para
	 *               finalizar su estacionamiento.
	 * @return Información variada sobre el servicio otorgado
	 */
	public String finalizarEstacionamiento(int número) {
		Estacionamiento estacionamiento = this.estacionamientosEnCurso.stream()
				.filter(est -> est.getCelular() != null && est.getCelular().getNúmero() == número).toList().get(0);

		this.estacionamientosEnCurso
				.removeIf(est -> est.getCelular() != null && est.getCelular().getNúmero() == número);
		this.descontarCrédito(estacionamiento.getCelular(), this.consultarSaldo(estacionamiento.getCelular()));

		String info = this.retornarInfo(estacionamiento.getHorarioInicio(), estacionamiento.getHorarioFin(),
				estacionamiento.duración(), this.consultarSaldo(estacionamiento.getCelular()));

		this.setChanged();
		this.notifyObservers(info);
		;
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

	public void descontarCrédito(Celular celular, int monto) {
		this.saldos.put(celular, this.saldos.get(celular) - monto);
	}

	public boolean créditoSuficiente(Celular celular, int horas) {
		return this.saldos.get(celular) / 40 >= horas;
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
	 * Añade el monto al saldo actual del celular, si no esta registrado lo registra y carga el celular con ese monto
	 * 
	 * @param celular 	Key
	 * @param monto		Value
	 */
	public void cargarCrédito(Celular celular, int monto) {
		if(this.saldos.containsKey(celular))
		    this.saldos.put(celular, monto + this.saldos.get(celular));
		else
		    this.saldos.put(celular, monto);
	}

	/*
	 * String.valueOf(estacionamiento.getHorarioInicio()) +
	 * String.valueOf(estacionamiento.getHorarioFin()) +
	 * String.valueOf(estacionamiento.duración()) +
	 * String.valueOf(estacionamiento.getCelular().getCrédito());
	 */

}
