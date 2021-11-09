package tpfinal;

import java.util.List;
import java.util.ArrayList;

public class SEM {

	private List<ZonaDeSem> zonasDeEstacionamiento = new ArrayList<ZonaDeSem>();
	private List<Estacionamiento> estacionamientosEnCurso = new ArrayList<Estacionamiento>();
	private List<Compra> compras = new ArrayList<Compra>();
	private List<Infracción> infracciones = new ArrayList<Infracción>();

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

	public void addInfracción() {
		// Este espacio depende de la creación del sector de infraciones.
	}

	public void addCompra(Compra compra) {
		this.compras.add(compra);
	}

	public String iniciarEstacionamiento(Celular celular, String patente, int hora) {
		int finDeEstacionamiento = this.calcularFinalDeEstacionamiento(celular, hora);
		if (finDeEstacionamiento >= hora) {
			EstacionamientoApp operación = new EstacionamientoApp(patente, hora, finDeEstacionamiento,
					celular.getNúmero());
			this.addEstacionamiento(operación);
			return "Puede"; // Ayudaaaa
		} else {
			return "No puede"; // Ayuda 2
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

	
	public String finalizarEstacionamiento(int número) {
		this.estacionamientosEnCurso.removeIf(est -> est.getCelular() == número);
		return "Data"; //Ayuda 3.
	}

	public List<Compra> getCompras() {
		return compras;
	}
	
	
	

}
