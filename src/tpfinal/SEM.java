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
		if (finDeEstacionamiento > hora && hora < 20 && this.cr�ditoSuficiente(celular, finDeEstacionamiento - hora)) {
			EstacionamientoApp operaci�n = new EstacionamientoApp(patente, celular);
			String info = "Su estacionamiento es valido desde las " + String.valueOf(hora) + "hs. " + "Hasta las "
					+ String.valueOf(finDeEstacionamiento) + "hs.";
			this.addEstacionamiento(operaci�n);

			return info;
		} else {

			return "Saldo Insuficiente. Estacionamiento no permitido";

		}

	}

	
	/**
	 * 
	 * @param celular Celular a obtener el saldo
	 * @return El saldo del celular o 0 si el celular nunca cargo saldo y no esta en el sistema
	 */
	public int consultarSaldo(Celular celular) {
		return this.saldos.getOrDefault(celular, 0);
	}

	/**
	 * 
	 * @param celular      De tipo Celular
	 * @param horaDeCompra de tipo int
	 * @return Un n�mero que representa la hora de finalizaci�n de un
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
	 * @param celular El n�mero del celular del cliente, lo unico requerido para
	 *               finalizar su estacionamiento.
	 * @return Informaci�n variada sobre el servicio otorgado
	 */
	public String finalizarEstacionamiento(Celular celular) {
		Estacionamiento estacionamiento = this.estacionamientosEnCurso.stream()
				.filter(est -> est.getCelular() != null && est.getCelular()== celular).toList().get(0);

		this.estacionamientosEnCurso
				.removeIf(est -> est.getCelular() != null && est.getCelular() == celular);
		this.descontarCr�dito(estacionamiento.getCelular(), this.consultarSaldo(estacionamiento.getCelular()));

		String info = this.retornarInfo(estacionamiento.getHorarioInicio(), estacionamiento.getHorarioFin(),
				estacionamiento.duraci�n(), this.consultarSaldo(estacionamiento.getCelular()));

		this.setChanged();
		this.notifyObservers(info);
		;
		return info;
	}

	public String retornarInfo(LocalTime inicio, LocalTime fin, int duraci�n, int cr�dito) {
		return "Hora de Inicio: " + String.valueOf(inicio) + "hs. Hora de fin: " + String.valueOf(fin)
				+ "hs. Duraci�n: " + String.valueOf(duraci�n) + "hs. Cr�dito restante: " + String.valueOf(cr�dito);
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public List<Estacionamiento> getEstacionamientosEnCurso() {
		return estacionamientosEnCurso;
	}

	/**
	 * Descuenta saldo al celular
	 * @param celular	Celular a descontar saldo
	 * @param monto		Monto a descontar
	 */
	private void descontarCr�dito(Celular celular, int monto) {
		this.saldos.put(celular, this.consultarSaldo(celular) - monto);
	}

	private boolean cr�ditoSuficiente(Celular celular, int horas) {
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
	 * A�ade el monto al saldo actual del celular, si no esta registrado en el sistema lo registra y carga el celular con ese monto
	 * 
	 * @param celular 	Key
	 * @param monto		Value
	 */
	public void cargarCr�dito(Celular celular, int monto) {
		    this.saldos.put(celular, monto + this.consultarSaldo(celular));
	}

	/*
	 * String.valueOf(estacionamiento.getHorarioInicio()) +
	 * String.valueOf(estacionamiento.getHorarioFin()) +
	 * String.valueOf(estacionamiento.duraci�n()) +
	 * String.valueOf(estacionamiento.getCelular().getCr�dito());
	 */

}
