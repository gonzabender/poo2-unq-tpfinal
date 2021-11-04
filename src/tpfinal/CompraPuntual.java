package tpfinal;

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
	 * @param horasCompradas la cantidad de horas a comprar.
	 */
	public CompraPuntual(int horasCompradas) {
		super();
		this.horasCompradas = horasCompradas;
	}

}
