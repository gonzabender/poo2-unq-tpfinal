package tpfinal;

import java.util.List;
import java.util.ArrayList;

public class SEM {

	private List<ZonaDeSem> zonasDeEstacionamiento = new ArrayList<ZonaDeSem>();
	private List<Estacionamiento> estacionamientosEnCurso = new ArrayList<Estacionamiento>();
	private List<Compra> compras = new ArrayList<Compra>();
	private List<Infracci�n> infracciones = new ArrayList<Infracci�n>();

	/**
	 * 
	 * @param zona La zona a agregar.
	 */
	public void addZona(ZonaDeSem zona) {
		zonasDeEstacionamiento.add(zona);
	}

	/**
	 * 
	 * @param estacionamiento El estacionamiento a agregar.
	 */
	public void addEstacionamiento(Estacionamiento estacionamiento) {
		estacionamientosEnCurso.add(estacionamiento);
	}

	public void addInfracci�n() {
		// Este espacio depende de la creaci�n del sector de infraciones.
	}

	public void addCompra(Compra compra) {
		this.compras.add(compra);
	}

	public String iniciarEstacionamiento(Celular celular, String patente, int hora) {
		int finDeEstacionamiento = this.calcularFinalDeEstacionamiento(celular, hora);
		if (finDeEstacionamiento >= hora) {
			EstacionamientoApp operaci�n = new EstacionamientoApp(patente, hora, finDeEstacionamiento,
					celular.getN�mero());
			this.addEstacionamiento(operaci�n);
			return "Puede"; // Ayudaaaa
		} else {
			return "No puede"; // Ayuda 2
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
		this.estacionamientosEnCurso.removeIf(est -> est.getCelular() == n�mero);
		return "Data"; //Ayuda 3.
	}

	public List<Compra> getCompras() {
		return compras;
	}
	
	
	

}
