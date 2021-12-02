package tpfinal.compras.estacionamientos;

import java.time.LocalTime;

public abstract class Estacionamiento {

	private String patente;
	private LocalTime horarioInicio;
	private LocalTime horarioFin;

	/**
	 * 
	 * @return Verdadero si el estacionamiento en curso se encuentra dentro del
	 *         horario abonado, falso sino.
	 */
	public boolean vigente() {
		return horarioFin.getHour() > horarioInicio.getHour();
	}

	public Estacionamiento(String patente, LocalTime horaInicio) {
		this.patente = patente;
		this.horarioInicio = horaInicio;
	}

	public void setHorarioFin(LocalTime horarioFin) {
		this.horarioFin = horarioFin;
	}

	public String getPatente() {
		return this.patente;
	}

	public LocalTime getHorarioInicio() {
		return horarioInicio;
	}

	public LocalTime getHorarioFin() {
		return horarioFin;
	}

	public int duración() {
		return horarioFin.getHour() - horarioInicio.getHour();
	}

	/**
	 * Hook method
	 */
	public void terminarEstacionamiento() {

	}

}
