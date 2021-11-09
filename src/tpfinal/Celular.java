package tpfinal;

public class Celular {
	
	private AppUsuario app;
	private int número;
	private int crédito;
	
	public void iniciarEstacionamiento() {
		app.iniciarEstacionamiento();
	}

	public int getNúmero() {
		return número;
	}

	public void cargarCrédito(int monto) {
		this.crédito = crédito + monto;
	}

	public int getCrédito() {
		return this.crédito;
	}


}
