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

}
