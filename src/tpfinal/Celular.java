package tpfinal;

public class Celular {

	private AppUsuario app;
	private int número;

	public int getNúmero() {
		return número;
	}

	public Celular(AppUsuario app, int número, int crédito) {
		this.app = app;
		this.número = número;
	}

	public void alerta(String texto) {
		// Imprime el mensaje que recibe en pantalla
		System.out.print(texto);
	}

}
