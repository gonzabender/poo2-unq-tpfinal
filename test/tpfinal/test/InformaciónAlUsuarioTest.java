package tpfinal.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import tpfinal.*;

public class InformaciónAlUsuarioTest {

	SEM sem;
	Celular iphone;
	AppUsuario app;
	PuntoDeVenta kiosco;

	@Before
	public void setUp() throws Exception {
		// Set up

		sem = new SEM();
		iphone = new Celular(app, 1157990244, 0);
		app = new AppUsuario(sem, "UNQ021", iphone);
		kiosco = new PuntoDeVenta();
		kiosco.setSem(sem);
	}

	@Test
	public void testInformaciónEstacionamientoExitoso() {
		// Exercise
		app.setHoraActual(9);
		app.cargarCredito(kiosco, 120);
		String data = app.iniciarEstacionamiento();

		// Verify
		assertEquals(data, app.iniciarEstacionamiento());
	}

	@Test
	public void testEstacionamientoNoPermitido() {
		// Exercise
		app.setHoraActual(9);
		app.cargarCredito(kiosco, 40);
		String data = app.iniciarEstacionamiento();

		// Verify
		assertEquals(data, app.iniciarEstacionamiento());
	}

	@Test
	public void testFinDeEstacionamiento() {
		// Exercise
		app.setHoraActual(9);
		app.cargarCredito(kiosco, 120);
		String data = app.iniciarEstacionamiento();
		String data2 = app.finalizarEstacionamiento();

		// Verify
		assertTrue(sem.getEstacionamientosEnCurso().size() == 1);
		assertEquals(data2, app.finalizarEstacionamiento());
	}

}
