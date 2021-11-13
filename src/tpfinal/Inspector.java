package tpfinal;

public class Inspector {
	
	private SEM sem;
	private ZonaSem zona;
	
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
	
	public boolean verificarEstacionamiento(String patente) {
		if (!this.consultaVigencia(patente)) {
			this.altaInfraccion(patente);
			return false;
		}
		else {
			return true;
		}
	}
	
	
}
