package tpfinal;

import java.util.Calendar;

public abstract class Compra {

	private Calendar date;
	private PuntoDeVenta puntoDeVenta;

	public Compra(Calendar date, PuntoDeVenta puntoDeVenta) {
		this.date = date;
		this.puntoDeVenta = puntoDeVenta;
	}

}
