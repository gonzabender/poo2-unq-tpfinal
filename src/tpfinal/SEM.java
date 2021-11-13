package tpfinal;

import java.util.List;
import java.util.ArrayList;

public class SEM {

	private List<ZonaSem> zonasDeEstacionamiento = new ArrayList<ZonaSem>();
	private List<Estacionamiento> estacionamientosEnCurso;
	private List<Compra> compras;
	private List<Infraccion> infracciones = new ArrayList<Infraccion>();
	private int horaActual;

	public SEM() {
		this.estacionamientosEnCurso = new ArrayList<Estacionamiento>();
		this.compras = new ArrayList<Compra>();
	}

	public void addZona(ZonaSem zona) {
		zonasDeEstacionamiento.add(zona);
	}

	public void addEstacionamiento(Estacionamiento estacionamiento) {
		estacionamientosEnCurso.add(estacionamiento);
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
		if (finDeEstacionamiento > hora && hora <= 20 && this.créditoSuficiente(celular, finDeEstacionamiento - hora)) {
			EstacionamientoApp operación = new EstacionamientoApp(patente, hora, finDeEstacionamiento, celular);
			this.addEstacionamiento(operación);
			return "Su estacionamiento es valido desde las " + String.valueOf(hora) + "hs. " + "Hasta las "
					+ String.valueOf(finDeEstacionamiento) + "hs.";
		} else {
			return "Saldo Insuficiente. Estacionamiento no permitido";
		}
	}

	public int consultarSaldo(Celular celular) {
		return celular.getCrédito();
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
				.filter(est -> est.getCelular().getNúmero() == número).toList().get(0);
		this.estacionamientosEnCurso.removeIf(est -> est.getCelular().getNúmero() == número);
		this.descontarCrédito(estacionamiento.getCelular(), estacionamiento.getCelular().getCrédito());
		return this.retornarInfo(estacionamiento.getHorarioInicio(), estacionamiento.getHorarioFin(),
				estacionamiento.duración(), estacionamiento.getCelular().getCrédito());
	}

	public String retornarInfo(int inicio, int fin, int duración, int crédito) {
		return String.valueOf(inicio) + String.valueOf(fin) + String.valueOf(duración) + String.valueOf(crédito);
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public List<Estacionamiento> getEstacionamientosEnCurso() {
		return estacionamientosEnCurso;
	}

	public void descontarCrédito(Celular celular, int monto) {
		celular.setCrédito(celular.getCrédito() - monto);
	}

	public boolean créditoSuficiente(Celular celular, int horas) {
		return celular.getCrédito() / 40 >= horas;
	}

	public void finForzosoDeEstacionamiento() {

	}

	public List<Infraccion> getInfracciones() {
		return infracciones;
	}

	public int getHoraActual() {
		return horaActual;
	}

	/*
	 * String.valueOf(estacionamiento.getHorarioInicio()) +
	 * String.valueOf(estacionamiento.getHorarioFin()) +
	 * String.valueOf(estacionamiento.duración()) +
	 * String.valueOf(estacionamiento.getCelular().getCrédito());
	 */

}
