package tpfinal;

public abstract class Compra {

	private String date;
	private PuntoDeVenta puntoDeVenta;

	public Compra(String date, PuntoDeVenta puntoDeVenta) {
		this.date = date;
		this.puntoDeVenta = puntoDeVenta;
	}

}
