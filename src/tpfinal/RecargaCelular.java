package tpfinal;

public class RecargaCelular extends Compra{
	
	private int monto;
	//private Celular celular;
	//o...
	//private int nroCelular;

	
	/**
	 * 
	 * @param nroCompra de la clase abstracta Compra.
	 * @param date Fecha de compra
	 * @param monto Cantidad de dinero a recargar.
	 */
	public RecargaCelular(int nroCompra, String date, int monto) {
		super(nroCompra, date);
		this.monto = monto;
	}

	
	

}
