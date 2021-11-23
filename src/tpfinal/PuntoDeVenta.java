package tpfinal;

import java.time.LocalTime;
import java.util.Calendar;

public class PuntoDeVenta {

	private SEM sem;

	/**
	 * 
	 * @param celular El objeto celular a recargar
	 * @param monto La cantidad de dinero a ingresar
	 */
	public void cargarCelular(Celular celular, int monto) {
		RecargaCelular operaci�n = new RecargaCelular(Calendar.getInstance(), this, monto, celular);
		celular.cargarCr�dito(monto);
		sem.addCompra(operaci�n);
	}

	/**
	 * 
	 * @param patente EL conductor indica la patente de su veh�culo
	 * @param horaFin Es establecida al realizar la operaci�n
	 */
	public void iniciarEstacionamiento(String patente, LocalTime horaFin) {
		CompraPuntual compra = new CompraPuntual(Calendar.getInstance(), this);
		EstacionamientoCompraPuntual operaci�n = new EstacionamientoCompraPuntual(patente, compra);
		operaci�n.setHorarioFin(horaFin);
		compra.setHorasCompradas(this.cantidadDeHorasTotales(operaci�n.getHorarioInicio(), horaFin));
		sem.addCompra(compra);
		sem.addEstacionamiento(operaci�n);
	}

	/**
	 * 
	 * @param a Hora inicial
	 * @param b Hora final a comparar
	 * @return La diferencia entre ambas horas
	 */
	private int cantidadDeHorasTotales(LocalTime a, LocalTime b) {
		return a.compareTo(b);
	}

	public void setSem(SEM sem) {
		this.sem = sem;
	}

}
