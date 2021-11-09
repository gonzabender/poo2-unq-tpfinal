package tpfinal;

public abstract class Estacionamiento {
	
	private String patente;
	private int horarioInicio;
	private int horarioFin;
	
	
	/**
	 * 
	 * @return Verdadero si el estacionamiento en curso se encuentra dentro
	 * del horario abonado, falso sino.
	 */
	public boolean vigente() {
		return horarioInicio <= horarioFin;
	}


	public Estacionamiento(String patente, int horarioInicio, int horarioFin) {
		this.patente = patente;
		this.horarioInicio = horarioInicio;
		this.horarioFin = horarioFin;
	}


	protected abstract int getCelular();
	
	

}
