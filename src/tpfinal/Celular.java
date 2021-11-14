package tpfinal;

public class Celular {

	private AppUsuario app;
	private int número;
	private int crédito;

	public int getNúmero() {
		return número;
	}

	public void setCrédito(int crédito) {
		this.crédito = crédito;
	}

	public void cargarCrédito(int monto) {
		this.crédito = crédito + monto;
	}

	public int getCrédito() {
		return this.crédito;
	}

	public Celular(AppUsuario app, int número, int crédito) {
		this.app = app;
		this.número = número;
		this.crédito = crédito;
	}

	public void alerta(String texto) {
		// Imprime el mensaje que recibe en pantalla
		System.out.print(texto);
	}

}
