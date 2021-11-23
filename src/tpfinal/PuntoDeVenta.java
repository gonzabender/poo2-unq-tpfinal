package tpfinal;

import java.time.LocalTime;
import java.util.Calendar;

public class PuntoDeVenta {

	private SEM sem;

	public void cargarCelular(Celular celular, int monto) {
		RecargaCelular operación = new RecargaCelular(Calendar.getInstance(), this, monto, celular);
		celular.cargarCrédito(monto);
		sem.addCompra(operación);
	}

	public void iniciarEstacionamiento(String patente, LocalTime horaFin) {
		CompraPuntual compra = new CompraPuntual(Calendar.getInstance(), this);
		EstacionamientoCompraPuntual operación = new EstacionamientoCompraPuntual(patente, compra);
		operación.setHorarioFin(horaFin);
		compra.setHorasCompradas(this.cantidadDeHorasTotales(operación.getHorarioInicio(), horaFin));
		sem.addCompra(compra);
		sem.addEstacionamiento(operación);
	}

	private int cantidadDeHorasTotales(LocalTime a, LocalTime b) {
		return a.compareTo(b);
	}

	public void setSem(SEM sem) {
		this.sem = sem;
	}

}
