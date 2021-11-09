package tpfinal;

import java.util.Calendar;

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

	public CompraPuntual(String date, PuntoDeVenta puntoDeVenta, int horasCompradas) {
		super(date, puntoDeVenta);
		this.horasCompradas = horasCompradas;
	}

}
