package tpfinal.compras.estacionamientos;

import java.time.LocalTime;

import tpfinal.app.usuario.Celular;

public class EstacionamientoApp extends Estacionamiento {

	private Celular celular;

	/**
	 * 
	 * @param patente       La patente del coche del usuario.
	 * @param horarioInicio Inicio de estacionamiento, automático en la app.
	 * @param horarioFin    Fin de estacionamiento, automático en la app.
	 * @param celular       El celular que realizo la compra.
	 */
	public EstacionamientoApp(String patente,  Celular celular) {
		super(patente);
		this.celular = celular;
	}

	public Celular getCelular() {
		return celular;
	}
	
	public void terminarEstacionamiento() {
		this.celular.restarSaldo(this.calcularResta());
	}
	
	public int calcularResta() {
		return (this.getHorarioFin().getHour() - this.getHorarioInicio().getHour()) *40;
	}

}
