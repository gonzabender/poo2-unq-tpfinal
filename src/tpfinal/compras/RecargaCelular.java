package tpfinal.compras;

import java.util.Calendar;

import tpfinal.app.usuario.Celular;
import tpfinal.puntoDeVenta.PuntoDeVenta;

public class RecargaCelular extends Compra {

	/**
	 * Se crea una nueva RecargaCelular cada vez que el cliente cargue su celular en
	 * un PuntoDeVenta
	 */

	private int monto;
	private Celular celular;

	/**
	 * 
	 * @param nroCompra de la clase abstracta Compra.
	 * @param date      Fecha de compra
	 * @param monto     Cantidad de dinero a recargar.
	 */
	public RecargaCelular(Calendar date, PuntoDeVenta puntoDeVenta, int monto, Celular celular) {
		super(date, puntoDeVenta);
		this.monto = monto;
		this.celular = celular;
	}

}
