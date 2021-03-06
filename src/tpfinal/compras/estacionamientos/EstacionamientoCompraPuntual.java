package tpfinal.compras.estacionamientos;

import java.time.LocalTime;

import tpfinal.compras.CompraPuntual;

public class EstacionamientoCompraPuntual extends Estacionamiento {

	@SuppressWarnings("unused")
	private CompraPuntual compra;

	/**
	 * 
	 * @param patente       Del coche del usuario quien realiz? la compra.
	 * @param horarioInicio Hora de inicio, en el momento de la compra.
	 * @param horarioFin    Hora de finalizar estacionamiento, previamente patada.
	 * @param compra        El punto de compra del estacionamiento.
	 * @param horaInicio 
	 */
	public EstacionamientoCompraPuntual(String patente, CompraPuntual compra, LocalTime horaInicio) {
		super(patente, horaInicio);
		this.compra = compra;
	}
	
	

}
