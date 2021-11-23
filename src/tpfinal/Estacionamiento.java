package tpfinal;

import java.time.LocalTime;
import java.util.Calendar;

public abstract class Estacionamiento {

	/**
	 * Mucho comportaiento para ser una clase abstracta, revisar.
	 */
	private String patente;
	private LocalTime horarioInicio;
	private LocalTime horarioFin;
	private Celular celular;

	/**
	 * 
	 * @return Verdadero si el estacionamiento en curso se encuentra dentro del
	 *         horario abonado, falso sino.
	 */
	public boolean vigente() {
		return horarioInicio.isBefore(horarioFin);
	}

	public Estacionamiento(String patente) {
		this.patente = patente;
		this.horarioInicio = LocalTime.now();
	}

	public void setHorarioFin(LocalTime horarioFin) {
		this.horarioFin = horarioFin;
	}
	

	public void setCelular(Celular celular) {
		this.celular = celular;
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
		return horarioInicio.compareTo(horarioFin);
	}

	 
	public Celular getCelular() {
		return celular;
	}

}
