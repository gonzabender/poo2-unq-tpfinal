package tpfinal.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
		// Set up

		sem = new SEM();
		kiosco = new PuntoDeVenta(sem);
		iphone = new Celular(app, 118594729, 0);
		app = new AppUsuario(sem, "PO2UNQ", iphone);
	}

	@Test
	public void testCargaCelular() {
		// Exercise
		kiosco.cargarCelular(iphone, 120);

		// Verify
		assertEquals(iphone.getCrédito(), 120);
		assertTrue(sem.getCompras().size() == 1);

	}

}
