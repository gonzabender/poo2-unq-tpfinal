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
		RecargaCelular operación = new RecargaCelular(Calendar.getInstance(), this, monto, celular);
		celular.cargarCrédito(monto);
		sem.addCompra(operación);
	}

	/**
	 * 
	 * @param patente EL conductor indica la patente de su vehículo
	 * @param horaFin Es establecida al realizar la operación
	 */
	public void iniciarEstacionamiento(String patente, LocalTime horaFin) {
		CompraPuntual compra = new CompraPuntual(Calendar.getInstance(), this);
		EstacionamientoCompraPuntual operación = new EstacionamientoCompraPuntual(patente, compra);
		operación.setHorarioFin(horaFin);
		compra.setHorasCompradas(this.cantidadDeHorasTotales(operación.getHorarioInicio(), horaFin));
		sem.addCompra(compra);
		sem.addEstacionamiento(operación);
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
