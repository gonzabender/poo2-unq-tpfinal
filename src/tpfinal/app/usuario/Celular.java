package tpfinal.app.usuario;

import java.util.Stack;

public class Celular {

	private AppUsuario app;
	private int n�mero;
	private int saldo;
	private Stack<String> alertas;
	
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
	
	public void cargarSaldo(int saldoCargar) {
		this.saldo += saldoCargar;
	}
	public Celular(AppUsuario app, int n�mero, int cr�dito) {
		this.app = app;
		this.n�mero = n�mero;
		this.saldo=cr�dito;
		this.alertas=new Stack <String>();
	}

	public void alerta(String texto) {
		this.alertas.push(texto);
	}

	public String ultimaAlerta() {
		return this.alertas.peek();
	}
	
	public void descartarUltimaAlerta() {
		this.alertas.pop();
	}

}
