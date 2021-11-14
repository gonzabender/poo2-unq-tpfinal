package tpfinal;

import java.util.Calendar;

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

	public Calendar getFechaYHora() {
		return fechaYHora;
	}

	public String getPatente() {
		return patente;
	}

	public Inspector getInspector() {
		return inspector;
	}

	public ZonaSem getZonasem() {
		return zonasem;
	}

}
