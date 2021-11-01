package tpfinal;

public abstract class AppUsuario {


	private SEM sem;
	private String patente;
	private int celular;
	
	public AppUsuario(SEM sem, String patente, int celular) {
		this.sem=sem;
		this.celular=celular;
		this.patente=patente;
	}

	public SEM getSem() {
		return sem;
	}

	public String getPatente() {
		return patente;
	}

	public int getCelular() {
		return celular;
	}

	private void setSem(SEM sem) {
		this.sem=sem;
	}
	
	public void cargarCredito(PuntoDeVenta pv) {
		pv.cargarCredito(this.celular);
	}

	//devuelve el saldo disponible
	public int consultaSaldo() {
		return this.sem.consultarSaldo(this.celular);
	}
	
	//Factory method
	abstract AppUsuario cambiarModo();
	
	abstract void alertaInicioE();

	abstract void alertaFinE();
	
}