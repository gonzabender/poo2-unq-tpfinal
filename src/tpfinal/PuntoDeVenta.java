package tpfinal;

public class PuntoDeVenta {
	
	private SEM sem;
	
	public void cargarCelular(Celular celular, int monto) {
		RecargaCelular operaci�n = new RecargaCelular(null, this, monto, celular.getN�mero());
		celular.cargarCr�dito(monto);
		sem.addCompra(operaci�n);		
	}

}
