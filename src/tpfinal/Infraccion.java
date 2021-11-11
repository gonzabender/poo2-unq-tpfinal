package tpfinal;

import java.util.Calendar;

public class Infraccion {
	
	Calendar fechaYHora;
	String patente;
	Inspector inspector;
	ZonaSem zonasem;
	
	public Infraccion(Inspector inspector, ZonaSem zonaSem, String patente) {
		this.fechaYHora = Calendar.getInstance();
		this.inspector = inspector;
		this.zonasem = zonaSem;
		this.patente = patente;
	}

	public Calendar getFechaYHora() {
		return fechaYHora;
	}

	public void setFechaYHora(Calendar fechaYHora) {
		this.fechaYHora = fechaYHora;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public Inspector getInspector() {
		return inspector;
	}

	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}

	public ZonaSem getZonasem() {
		return zonasem;
	}

	public void setZonasem(ZonaSem zonasem) {
		this.zonasem = zonasem;
	}
	
	
}
