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
		if (finDeEstacionamiento > hora && hora <= 20 && this.cr�ditoSuficiente(celular, finDeEstacionamiento - hora)) {
			EstacionamientoApp operaci�n = new EstacionamientoApp(patente, hora, finDeEstacionamiento, celular);
			this.addEstacionamiento(operaci�n);
			return "Su estacionamiento es valido desde las " + String.valueOf(hora) + "hs. " + "Hasta las "
					+ String.valueOf(finDeEstacionamiento) + "hs.";
		} else {
			return "Saldo Insuficiente. Estacionamiento no permitido";
		}
	}

	public int consultarSaldo(Celular celular) {
		return celular.getCr�dito();
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
	 * @param n�mero El n�mero del celular del cliente, lo unico requerido para
	 *               finalizar su estacionamiento.
	 * @return Informaci�n variada sobre el servicio otorgado
	 */
	public String finalizarEstacionamiento(int n�mero) {
		Estacionamiento estacionamiento = this.estacionamientosEnCurso.stream()
				.filter(est -> est.getCelular().getN�mero() == n�mero).toList().get(0);
		this.estacionamientosEnCurso.removeIf(est -> est.getCelular().getN�mero() == n�mero);
		this.descontarCr�dito(estacionamiento.getCelular(), estacionamiento.getCelular().getCr�dito());
		return this.retornarInfo(estacionamiento.getHorarioInicio(), estacionamiento.getHorarioFin(),
				estacionamiento.duraci�n(), estacionamiento.getCelular().getCr�dito());
	}

	public String retornarInfo(int inicio, int fin, int duraci�n, int cr�dito) {
		return String.valueOf(inicio) + String.valueOf(fin) + String.valueOf(duraci�n) + String.valueOf(cr�dito);
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public List<Estacionamiento> getEstacionamientosEnCurso() {
		return estacionamientosEnCurso;
	}

	public void descontarCr�dito(Celular celular, int monto) {
		celular.setCr�dito(celular.getCr�dito() - monto);
	}

	public boolean cr�ditoSuficiente(Celular celular, int horas) {
		return celular.getCr�dito() / 40 >= horas;
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
	 * String.valueOf(estacionamiento.duraci�n()) +
	 * String.valueOf(estacionamiento.getCelular().getCr�dito());
	 */

}
