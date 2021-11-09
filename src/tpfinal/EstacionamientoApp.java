package tpfinal;

public class EstacionamientoApp extends Estacionamiento{
	
	private int celular;  //dar� error hasta crear la clase celular...
	
	

	/**
	 * 
	 * @param patente La patente del coche del usuario.
	 * @param horarioInicio Inicio de estacionamiento, autom�tico en la app.
	 * @param horarioFin Fin de estacionamiento, autom�tico en la app.
	 * @param celular El celular que realizo la compra.
	 */
	public EstacionamientoApp(String patente, int horarioInicio, int horarioFin, int celular) {
		super(patente, horarioInicio, horarioFin);
		this.celular = celular;
	}



	public int getCelular() {
		return celular;
	}
	
	
	
	

}
