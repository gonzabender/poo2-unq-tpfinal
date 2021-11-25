package tpfinal.compras;

import java.util.Calendar;

import tpfinal.sistema.PuntoDeVenta;

public abstract class Compra {

	private Calendar date;
	private PuntoDeVenta puntoDeVenta;

	public Compra(Calendar date, PuntoDeVenta puntoDeVenta) {
		this.date = date;
		this.puntoDeVenta = puntoDeVenta;
	}

}
