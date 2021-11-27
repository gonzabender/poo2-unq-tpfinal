package tpfinal.app.usuario;

import tpfinal.sistema.ZonaSem;

public class Celular {

	private AppUsuario app;
	private int n�mero;
	private int saldo;
	private ZonaSem gps;
	
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
	}

	public void alerta(String texto) {
		// Imprime el mensaje que recibe en pantalla
		System.out.print(texto);
	}

	
	/**
	 * Setea la posicion actual en el gps y le indica a la app si esta dentro de una zona o no
	 * @param posicion	representa la zona en la que esta, null en caso de que no este dentro de una zona
	 */
	public void setPosicion(ZonaSem posicion) {
		//podria cambiarse por "entroEnZona()" y "salioDeZona()"
		this.gps= posicion;
		if (posicion==null) {
			this.app.notificarFueraDeZona();
		} else {
			this.app.notificarDentroDeZona();
		}
		
	}

}
