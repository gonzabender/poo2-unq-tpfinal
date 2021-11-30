package tpfinal.compras;

import java.util.Calendar;

import tpfinal.puntoDeVenta.PuntoDeVenta;

/**
 * 
 * Se creara una nueva compra puntual cada vez que el cliente la compre en el
 * negocio.
 *
 */
public class CompraPuntual extends Compra {

	private int horasCompradas;

	/**
	 * 
	 * @param nroCompra      Generado de la clase abstracta Compra.
	 * @param date           Fecha de compra.
	 * @param horasCompradas cantidad de horas abonadas.
	 */

	public CompraPuntual(Calendar date, PuntoDeVenta puntoDeVenta) {
		super(date, puntoDeVenta);
	}

	public void setHorasCompradas(int horasCompradas) {
		this.horasCompradas = horasCompradas;
	}
	
	

}
