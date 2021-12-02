package tpfinal.informaciónAlUsuario;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tpfinal.app.usuario.AppUsuario;
import tpfinal.app.usuario.Celular;
import tpfinal.puntoDeVenta.PuntoDeVenta;
import tpfinal.sistema.SEM;

public class InformaciónDeInicio {

	/**
	 * Test para verificar la información que el usuario recibe en su celular al
	 * iniciar un estacionamiento exitoso mediante la aplicación.
	 */

	SEM sem;
	Celular iphone;
	AppUsuario app;
	PuntoDeVenta kiosco;

	// Para testear los prints de consola
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	PrintStream originalOut = System.out;

	@Before
	public void setUp() throws Exception {
		// Set up

		sem = new SEM();
		iphone = new Celular(app, 1157990244, 0);
		app = new AppUsuario(sem, "UNQ021", iphone);
		kiosco = new PuntoDeVenta(sem);

		// Para testear los prints de consola
		System.setOut(new PrintStream(outContent));

	}

	@Test
	public void testInformaciónEstacionamientoExitoso() {
		// Exercise
		app.setHoraActual(LocalTime.of(9, 30));
		sem.setHoraActual(LocalTime.of(9, 30));
		kiosco.cargarCelular(iphone, 120);
		;
		app.iniciarEstacionamiento();

		String data = "Su estacionamiento es valido desde las 09:30hs. Hasta las 12:30hs.";

		// Verify
		assertEquals(data, iphone.ultimaAlerta());
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
	}

}
