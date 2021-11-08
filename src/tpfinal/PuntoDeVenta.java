package tpfinal;

public class PuntoDeVenta {
	
	private SEM sem;
	
	public void cargarCelular(Celular celular, int monto) {
		RecargaCelular operaci�n = new RecargaCelular("hoy", this, monto, celular.getN�mero());
		celular.cargarCr�dito(monto);
		sem.addCompra(operaci�n);		
	}
	
	public void iniciarEstacionamiento(String patente, int horaInicio, int horaFin) {
		CompraPuntual compra = new CompraPuntual(patente, this, this.cantidadDeHorasTotales(horaInicio, horaFin));
		EstacionamientoCompraPuntual operaci�n = new EstacionamientoCompraPuntual(patente, horaInicio, horaFin, compra); 
		sem.addCompra(compra);
		sem.addEstacionamiento(operaci�n);
	}

	private int cantidadDeHorasTotales(int a, int b) {
		return b - a;
	}

}
