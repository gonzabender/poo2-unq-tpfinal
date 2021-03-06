package tpfinal.puntoDeVenta;

import java.time.LocalTime;
import java.util.Calendar;

import tpfinal.app.usuario.Celular;
import tpfinal.compras.CompraPuntual;
import tpfinal.compras.RecargaCelular;
import tpfinal.compras.estacionamientos.EstacionamientoCompraPuntual;
import tpfinal.sistema.SEM;

public class PuntoDeVenta {

	private SEM sem;

	/**
	 * 
	 * @param celular El objeto celular a recargar
	 * @param monto   La cantidad de dinero a ingresar
	 */
	public void cargarCelular(Celular celular, int monto) {
		RecargaCelular operaci?n = new RecargaCelular(Calendar.getInstance(), this, monto, celular);
		celular.cargarSaldo(monto);
		sem.addCompra(operaci?n);
	}

	/**
	 * 
	 * @param patente EL conductor indica la patente de su veh?culo
	 * @param horaFin Es establecida al realizar la operaci?n
	 */
	public void iniciarEstacionamiento(String patente, LocalTime horaFin) {
		CompraPuntual compra = new CompraPuntual(Calendar.getInstance(), this);
		EstacionamientoCompraPuntual operaci?n = new EstacionamientoCompraPuntual(patente, compra, sem.getHoraActual());
		operaci?n.setHorarioFin(horaFin);
		compra.setHorasCompradas(this.cantidadDeHorasTotales(operaci?n.getHorarioInicio(), horaFin));
		sem.addCompra(compra);
		sem.addEstacionamiento(operaci?n);
	}

	/**
	 * 
	 * @param a Hora inicial
	 * @param b Hora final a comparar
	 * @return La diferencia entre ambas horas
	 */
	private int cantidadDeHorasTotales(LocalTime a, LocalTime b) {
		return b.getHour() - a.getHour();
	}

	
	/**
	 * @param sem
	 */
	public PuntoDeVenta(SEM sem) {
		super();
		this.sem = sem;
	}


}
