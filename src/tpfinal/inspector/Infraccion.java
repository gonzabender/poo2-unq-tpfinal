package tpfinal.inspector;

import java.util.Calendar;

import tpfinal.sistema.ZonaSem;

public class Infraccion {

	@SuppressWarnings("unused")
	private Calendar fechaYHora;
	@SuppressWarnings("unused")
	private String patente;
	@SuppressWarnings("unused")
	private Inspector inspector;
	@SuppressWarnings("unused")
	private ZonaSem zonasem;

	public Infraccion(Inspector inspector, ZonaSem zonaSem, String patente) {
		this.fechaYHora = Calendar.getInstance();
		this.inspector = inspector;
		this.zonasem = zonaSem;
		this.patente = patente;
	}
	
}
