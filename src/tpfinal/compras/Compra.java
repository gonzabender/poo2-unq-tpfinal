package tpfinal.compras;

import java.util.Calendar;

import tpfinal.puntoDeVenta.PuntoDeVenta;

public abstract class Compra {

	@SuppressWarnings("unused")
	private Calendar date;
	@SuppressWarnings("unused")
	private PuntoDeVenta puntoDeVenta;

	public Compra(Calendar date, PuntoDeVenta puntoDeVenta) {
		this.date = date;
		this.puntoDeVenta = puntoDeVenta;
	}

}
