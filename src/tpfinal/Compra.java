package tpfinal;

import java.util.Calendar;

public abstract class Compra {
	
	private int nroCompra;
	private Calendar date;
	//private PuntoDeVenta puntoDeVenta;
	
	
	public Compra(int nroCompra, Calendar date) {
		this.nroCompra = nroCompra;
		this.date = date;
	}
	
	

}
