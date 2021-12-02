package tpfinal.inspector;

import java.util.Calendar;

import tpfinal.sistema.ZonaSem;

public class Infraccion {

	private Calendar fechaYHora;
	private String patente;
	private Inspector inspector;
	private ZonaSem zonasem;

	public Infraccion(Inspector inspector, ZonaSem zonaSem, String patente) {
		this.fechaYHora = Calendar.getInstance();
		this.inspector = inspector;
		this.zonasem = zonaSem;
		this.patente = patente;
	}
	
}
