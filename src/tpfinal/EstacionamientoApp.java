package tpfinal;

import java.time.LocalTime;

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
	
	@Override
	public void setCelular(Celular celular) {
		this.celular = celular;
	}

	public Celular getCelular() {
		return celular;
	}

}
