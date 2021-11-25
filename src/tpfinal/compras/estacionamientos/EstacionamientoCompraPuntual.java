package tpfinal.compras.estacionamientos;

import java.time.LocalTime;

import tpfinal.compras.CompraPuntual;

public class EstacionamientoCompraPuntual extends Estacionamiento {

	private CompraPuntual compra;

	/**
	 * 
	 * @param patente       Del coche del usuario quien realizó la compra.
	 * @param horarioInicio Hora de inicio, en el momento de la compra.
	 * @param horarioFin    Hora de finalizar estacionamiento, previamente patada.
	 * @param compra        El punto de compra del estacionamiento.
	 */
	public EstacionamientoCompraPuntual(String patente, CompraPuntual compra) {
		super(patente);
		this.compra = compra;
	}

}
