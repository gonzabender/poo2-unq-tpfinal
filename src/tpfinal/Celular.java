package tpfinal;

public class Celular {

	private AppUsuario app;
	private int n�mero;

	public int getN�mero() {
		return n�mero;
	}

	public Celular(AppUsuario app, int n�mero, int cr�dito) {
		this.app = app;
		this.n�mero = n�mero;
	}

	public void alerta(String texto) {
		// Imprime el mensaje que recibe en pantalla
		System.out.print(texto);
	}

}
