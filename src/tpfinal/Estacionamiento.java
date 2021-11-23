package tpfinal;

import java.time.LocalTime;
import java.util.Calendar;

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
		return horarioInicio.isBefore(horarioFin);
	}

	public Estacionamiento(String patente) {
		this.patente = patente;
		this.horarioInicio = LocalTime.now();
	}

	public void setHorarioFin(LocalTime horarioFin) {
		this.horarioFin = horarioFin;
	}
	

	/**
	 * Método hook
	 */
	public void setCelular(Celular celular) {
		
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

}
