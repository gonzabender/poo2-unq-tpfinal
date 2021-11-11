package tpfinal;

import java.util.List;
import java.util.ArrayList;

public class SEM {

	// private List<ZonaDeSem> zonasDeEstacionamiento = new ArrayList<ZonaDeSem>();
	private List<Estacionamiento> estacionamientosEnCurso;
	private List<Compra> compras;
	private List<Infraccion> infracciones = new ArrayList<Infraccion>();

	public SEM() {
		this.estacionamientosEnCurso = new ArrayList<Estacionamiento>();
		this.compras = new ArrayList<Compra>();
	}

	/**
	 * 
	 * @param zona La zona a agregar.
	 */
	/*
	 * public void addZona(ZonaDeSem zona) { zonasDeEstacionamiento.add(zona); }
	 */

	/**
	 * 
	 * @param estacionamiento El estacionamiento a agregar.
	 */
	public void addEstacionamiento(Estacionamiento estacionamiento) {
		estacionamientosEnCurso.add(estacionamiento);
	}

	public void addInfraccion(Infraccion i) {
		this.infracciones.add(i);
	}

	public void addCompra(Compra compra) {
		this.compras.add(compra);
	}

	public String iniciarEstacionamiento(Celular celular, String patente, int hora) {
		int finDeEstacionamiento = this.calcularFinalDeEstacionamiento(celular, hora);
		if (finDeEstacionamiento >= hora && finDeEstacionamiento != 20) {
			EstacionamientoApp operaci�n = new EstacionamientoApp(patente, hora, finDeEstacionamiento, celular);
			this.addEstacionamiento(operaci�n);
			return String.valueOf(hora) + String.valueOf(finDeEstacionamiento);
		} else {
			return "Saldo Insuficiente. Estacionamiento no permitido";
		}
	}

	public int consultarSaldo(Celular celular) {
		return celular.getCr�dito();
	}

	public int calcularFinalDeEstacionamiento(Celular celular, int horaDeCompra) {
		int saldo = this.consultarSaldo(celular);
		int horas = horaDeCompra;
		while (saldo > 0 || saldo < 40) {
			horas = horas + 1;
			saldo = saldo - 40;
		}
		return horas;
	}

	public String finalizarEstacionamiento(int n�mero) {
		Estacionamiento estacionamiento = this.estacionamientosEnCurso.stream()
				.filter(est -> est.getCelular().getN�mero() == n�mero).toList().get(0);
		this.estacionamientosEnCurso.removeIf(est -> est.getCelular().getN�mero() == n�mero);
		this.descontarCr�dito(estacionamiento.getCelular(), estacionamiento.getCelular().getCr�dito());
		return String.valueOf(estacionamiento.getHorarioInicio()) + String.valueOf(estacionamiento.getHorarioFin())
				+ String.valueOf(estacionamiento.duraci�n()) + String.valueOf(estacionamiento.getCelular().getCr�dito());
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

}
