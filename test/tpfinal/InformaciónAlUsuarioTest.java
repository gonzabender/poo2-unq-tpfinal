package tpfinal;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tpfinal.*;
import tpfinal.sistema.PuntoDeVenta;
import tpfinal.sistema.SEM;
import tpfinal.usuario.AppUsuario;
import tpfinal.usuario.Celular;

public class InformaciónAlUsuarioTest {

	SEM sem;
	Celular iphone;
	AppUsuario app;
	PuntoDeVenta kiosco;
	
	//Para testear los prints de consola
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	PrintStream originalOut = System.out;
	
	
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
		
	}

	@Test
	public void testInformaciónEstacionamientoExitoso() {
		// Exercise
		app.setHoraActual(9);
		kiosco.cargarCelular(iphone, 120);;
		app.iniciarEstacionamiento();
		
		String data =  "Su estacionamiento es valido desde las 9hs. Hasta las 12hs.";
		
		
		// Verify
		assertEquals(data,outContent.toString());
	}

	@Test
	public void testEstacionamientoNoPermitido() {
		// Exercise
		app.setHoraActual(9);
		kiosco.cargarCelular(iphone, 20);; // No llegaría a pagar ni una hora de estacionamiento
		app.iniciarEstacionamiento();
		String data = "Saldo Insuficiente. Estacionamiento no permitido";

		// Verify
		assertEquals(data,outContent.toString());
	}

	@Test
	public void testFinDeEstacionamiento() {
		// Exercise
		app.setHoraActual(9);
		kiosco.cargarCelular(iphone, 120);;
		String data = "Su estacionamiento es valido desde las 9hs. Hasta las 12hs."
						+"Hora de Inicio: 9hs. Hora de fin: 12hs. Duración: 3hs. Crédito restante: 0";

		app.iniciarEstacionamiento();
		app.finalizarEstacionamiento();

		// Verify
		assertEquals(data, outContent.toString());
	}
	
	
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	}

}
