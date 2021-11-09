package tpfinal;

public class PuntoDeVenta {
	
	private SEM sem;
	
	public SEM getSem() {
		return sem;
	}

	public void cargarCelular(Celular celular, int monto) {
		RecargaCelular operación = new RecargaCelular("hoy", this, monto, celular.getNúmero());
		celular.cargarCrédito(monto);
		sem.addCompra(operación);		
	}
	
	public void iniciarEstacionamiento(String patente, int horaInicio, int horaFin) {
		CompraPuntual compra = new CompraPuntual(patente, this, this.cantidadDeHorasTotales(horaInicio, horaFin));
		EstacionamientoCompraPuntual operación = new EstacionamientoCompraPuntual(patente, horaInicio, horaFin, compra); 
		sem.addCompra(compra);
		sem.addEstacionamiento(operación);
	}

	private int cantidadDeHorasTotales(int a, int b) {
		return b - a;
	}

	public void setSem(SEM sem) {
		this.sem = sem;
	}

	

}
