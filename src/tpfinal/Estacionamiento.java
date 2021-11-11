package tpfinal;

public abstract class Estacionamiento {

	private String patente;
	private int horarioInicio;
	private int horarioFin;

	/**
	 * 
	 * @return Verdadero si el estacionamiento en curso se encuentra dentro del
	 *         horario abonado, falso sino.
	 */
	public boolean vigente() {
		return horarioInicio <= horarioFin;
	}

	public Estacionamiento(String patente, int horarioInicio, int horarioFin) {
		this.patente = patente;
		this.horarioInicio = horarioInicio;
		this.horarioFin = horarioFin;
	}

	protected abstract Celular getCelular();

	public String getPatente() {
		return this.patente;
	}

	public int getHorarioInicio() {
		return horarioInicio;
	}

	public int getHorarioFin() {
		return horarioFin;
	}

	public int duración() {
		return this.getHorarioFin() - this.getHorarioInicio();
	}

}
