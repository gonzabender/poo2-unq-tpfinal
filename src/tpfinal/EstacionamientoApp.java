package tpfinal;

public class EstacionamientoApp extends Estacionamiento{
	
	private Celular celular;  
	
	

	/**
	 * 
	 * @param patente La patente del coche del usuario.
	 * @param horarioInicio Inicio de estacionamiento, automático en la app.
	 * @param horarioFin Fin de estacionamiento, automático en la app.
	 * @param celular El celular que realizo la compra.
	 */
	public EstacionamientoApp(String patente, int horarioInicio, int horarioFin, Celular celular) {
		super(patente, horarioInicio, horarioFin);
		this.celular = celular;
	}



	public Celular getCelular() {
		return celular;
	}
	
	
	
	

}
