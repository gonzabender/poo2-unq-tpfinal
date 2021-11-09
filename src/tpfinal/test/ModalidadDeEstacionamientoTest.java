package tpfinal.test;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import tpfinal.*;

public class ModalidadDeEstacionamientoTest {
	
	Celular iphone;
	AppUsuario app;
	PuntoDeVenta kiosco;
	SEM sem;
	
	@Before
	public void setUp() throws Exception {
		// Set up.
		
		sem = new SEM();
		iphone = new Celular(app, 118594729, 0);
		app = new AppUsuario(sem, "PO2UNQ", iphone);
		kiosco = new PuntoDeVenta();
	}
	
	@Test
	public void testCompraPuntual() {
		
	}

}
