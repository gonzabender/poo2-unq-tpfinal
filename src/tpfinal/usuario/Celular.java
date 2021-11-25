package tpfinal.usuario;

public class Celular {

	private AppUsuario app;
	private int n�mero;
	private int saldo;
	
	public int getN�mero() {
		return n�mero;
	}
	
	public int getSaldo() {
		return saldo;
	}
	
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	
	public void restarSaldo(int saldoRestar) {
		this.saldo -= saldoRestar;	
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
