package tpfinal.informaciónAlUsuario;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import tpfinal.app.usuario.AppUsuario;
import tpfinal.app.usuario.Celular;
import tpfinal.puntoDeVenta.PuntoDeVenta;
import tpfinal.sistema.SEM;

public class EstacionamientoFueraDeHorario {

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
	public void testEstacionamientoNoPermitido() {
		// Exercise
		app.setHoraActual(LocalTime.of(3, 0));
		sem.setHoraActual(LocalTime.of(3, 0));
		kiosco.cargarCelular(iphone, 80);
		app.iniciarEstacionamiento();
		String data = "No es requerido estacionar entre las 20:00hs y las 07:00hs";

		// Verify
		assertEquals(data, iphone.ultimaAlerta());
	}
}
