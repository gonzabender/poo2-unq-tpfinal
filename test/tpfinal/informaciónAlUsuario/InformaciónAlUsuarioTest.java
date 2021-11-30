package tpfinal.informaciónAlUsuario;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalTime;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tpfinal.*;
import tpfinal.app.usuario.AppUsuario;
import tpfinal.app.usuario.Celular;
import tpfinal.puntoDeVenta.PuntoDeVenta;
import tpfinal.sistema.SEM;

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
		app.setHoraActual(LocalTime.of(9, 30));
		sem.setHoraActual(LocalTime.of(9, 30));
		kiosco.cargarCelular(iphone, 120);;
		app.iniciarEstacionamiento();
		
		String data =  "Su estacionamiento es valido desde las 09:30hs. Hasta las 12:30hs.";
		
		
		// Verify
		assertEquals(data,iphone.ultimaAlerta());
	}

	@Test
	public void testEstacionamientoNoPermitido() {
		// Exercise
		app.setHoraActual(LocalTime.of(9, 0));
		sem.setHoraActual(LocalTime.of(9,0));
		kiosco.cargarCelular(iphone, 20);; // No llegaría a pagar ni una hora de estacionamiento
		app.iniciarEstacionamiento();
		String data = "No tiene credito suficiente para iniciar estacionamiento";

		// Verify
		assertEquals(data,iphone.ultimaAlerta());
	}

	@Test
	public void testFinDeEstacionamiento() {
		// Exercise
		app.setHoraActual(LocalTime.of(9, 0));
		sem.setHoraActual(LocalTime.of(9, 0));
		kiosco.cargarCelular(iphone, 120);
		String data = "Su estacionamiento es valido desde las 09:00hs. Hasta las 12:00hs.";
		String data2="Hora de Inicio: 09:00hs. Hora de fin: 11:00hs. Duración: 1hs. Crédito restante: 40";

		app.iniciarEstacionamiento();
		
		app.setHoraActual(LocalTime.of(11, 0));
		sem.setHoraActual(LocalTime.of(11, 0));
		
		app.finalizarEstacionamiento();

		// Verify
		assertEquals(data2, iphone.ultimaAlerta());
		iphone.descartarUltimaAlerta();
		assertEquals(data,iphone.ultimaAlerta());
	}
	
	
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	}

}
