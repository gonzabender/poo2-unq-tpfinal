package tpfinal.usuario;

public class Celular {

	private AppUsuario app;
	private int número;
	private int saldo;
	
	public int getNúmero() {
		return número;
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

	public Celular(AppUsuario app, int número, int crédito) {
		this.app = app;
		this.número = número;
	}

	public void alerta(String texto) {
		// Imprime el mensaje que recibe en pantalla
		System.out.print(texto);
	}

}
