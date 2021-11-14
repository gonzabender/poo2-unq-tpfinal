package tpfinal;

public class Celular {

	private AppUsuario app;
	private int n�mero;
	private int cr�dito;

	public int getN�mero() {
		return n�mero;
	}

	public void setCr�dito(int cr�dito) {
		this.cr�dito = cr�dito;
	}

	public void cargarCr�dito(int monto) {
		this.cr�dito = cr�dito + monto;
	}

	public int getCr�dito() {
		return this.cr�dito;
	}

	public Celular(AppUsuario app, int n�mero, int cr�dito) {
		this.app = app;
		this.n�mero = n�mero;
		this.cr�dito = cr�dito;
	}

	public void alerta(String texto) {
		// Imprime el mensaje que recibe en pantalla
		System.out.print(texto);
	}

}
