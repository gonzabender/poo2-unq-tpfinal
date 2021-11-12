package tpfinal.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tpfinal.*;

public class InformaciónAlUsuarioTest {

	SEM sem;
	Celular iphone;
	AppUsuario app;
	PuntoDeVenta kiosco;
	
	//Para testear los prints de consola
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	PrintStream originalOut = System.out;
	PrintStream originalErr = System.err;
	
	
	@Before
	public void setUp() throws Exception {
		// Set up

		sem = new SEM();
		iphone = new Celular(app, 1157990244, 0);
		app = new AppUsuario(sem, "UNQ021", iphone);
		kiosco = new PuntoDeVenta();
		kiosco.setSem(sem);
		
		//Para testear los prints de consola
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
		
	}

	@Test
	public void testInformaciónEstacionamientoExitoso() {
		// Exercise
		app.setHoraActual(9);
		app.cargarCredito(kiosco, 120);
		app.iniciarEstacionamiento();
		
		String data =  "Su estacionamiento es valido desde las 9hs. Hasta las 12hs.";
		
		
		// Verify
		assertEquals(data,outContent.toString());
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
	
	
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}

}
