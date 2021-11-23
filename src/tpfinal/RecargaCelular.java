package tpfinal;

import java.util.Calendar;

public class RecargaCelular extends Compra {

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
