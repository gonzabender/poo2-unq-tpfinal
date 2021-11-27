package tpfinal.app.usuario;

import java.util.Stack;

public class Celular {

	private AppUsuario app;
	private int número;
	private int saldo;
	private Stack<String> alertas;
	
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
	
	public void cargarSaldo(int saldoCargar) {
		this.saldo += saldoCargar;
	}
	public Celular(AppUsuario app, int número, int crédito) {
		this.app = app;
		this.número = número;
		this.saldo=crédito;
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
