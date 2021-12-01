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
	 * @param horaInicio
	 */
	public EstacionamientoApp(String patente, Celular celular, LocalTime horaInicio) {
		super(patente, horaInicio);
		this.celular = celular;
		this.celular.alerta("Su estacionamiento medido fue finalizado por haber alcanzado la hora límite del servicio");
	}

	public void terminarEstacionamiento() {
		this.setHorarioFin(LocalTime.of(20, 0));
		this.celular.restarSaldo(this.calcularResta());
	}

	public int calcularResta() {
		return (this.getHorarioFin().getHour() - this.getHorarioInicio().getHour()) * 40;
	}

}
