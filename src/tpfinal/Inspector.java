package tpfinal;

import java.util.Calendar;

public class Inspector {
	
	SEM sem;
	ZonaSem zona;
	
	public Inspector(SEM sem, ZonaSem zona) {
		this.sem = sem;
		this.zona = zona;
	}
	
	public SEM getSEM() {
		return this.sem;
	}
	
	public void setSEM(SEM sem) {
		this.sem = sem;
	}
	
	public ZonaSem getZonaSem() {
		return this.zona;
	}
	
	public void setZonaSem(ZonaSem zona) {
		this.zona = zona;
	}
	
	public boolean consultaVigencia(String patente) {
		return zona.estaVigente(patente);
	} 
	
	public void altaInfraccion(String patente) {
		Infraccion inf = new Infraccion(this, this.zona, patente);
		this.sem.addInfraccion(inf);
	}
	
	
	
}