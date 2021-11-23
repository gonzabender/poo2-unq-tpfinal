package tpfinal;

import java.util.Calendar;

public class PuntoDeVenta {

	private SEM sem;

	public void cargarCelular(Celular celular, int monto) {
		RecargaCelular operaci�n = new RecargaCelular(Calendar.getInstance(), this, monto, celular);
		celular.cargarCr�dito(monto);
		sem.addCompra(operaci�n);
	}

	public void iniciarEstacionamiento(String patente, int horaInicio, int horaFin) {
		CompraPuntual compra = new CompraPuntual(Calendar.getInstance(), this,
				this.cantidadDeHorasTotales(horaInicio, horaFin));
		EstacionamientoCompraPuntual operaci�n = new EstacionamientoCompraPuntual(patente, horaInicio, horaFin, compra);
		sem.addCompra(compra);
		sem.addEstacionamiento(operaci�n);
	}

	private int cantidadDeHorasTotales(int a, int b) {
		return b - a;
	}

	public void setSem(SEM sem) {
		this.sem = sem;
	}

}
