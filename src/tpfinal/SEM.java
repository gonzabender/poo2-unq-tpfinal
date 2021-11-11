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

	public void iniciarEstacionamiento(Celular celular, String patente, int hora) {
		int finDeEstacionamiento = this.calcularFinalDeEstacionamiento(celular, hora);
		if (finDeEstacionamiento >= hora) {
			EstacionamientoApp operación = new EstacionamientoApp(patente, hora, finDeEstacionamiento,
					celular.getNúmero());
			this.addEstacionamiento(operación);
			// return true; // deberia enviar info
		}
	}

	public int consultarSaldo(Celular celular) {
		return celular.getCrédito();
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

	public void finalizarEstacionamiento(int número) {
		this.estacionamientosEnCurso.removeIf(est -> est.getCelular() == número);
		//return "Data"; // Deberia enviar info
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public List<Estacionamiento> getEstacionamientosEnCurso() {
		return estacionamientosEnCurso;
	}
	
	

}
