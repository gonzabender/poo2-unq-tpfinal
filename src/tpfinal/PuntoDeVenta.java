package tpfinal;

public class PuntoDeVenta {
	
	private SEM sem;
	
	public void cargarCelular(Celular celular, int monto) {
		RecargaCelular operación = new RecargaCelular(null, this, monto, celular.getNúmero());
		celular.cargarCrédito(monto);
		sem.addCompra(operación);		
	}

}
