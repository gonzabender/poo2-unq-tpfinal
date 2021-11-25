package tpfinal;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tpfinal.*;
import tpfinal.sistema.EntidadAdapter;
import tpfinal.sistema.PuntoDeVenta;
import tpfinal.sistema.SEM;
import tpfinal.usuario.AppUsuario;
import tpfinal.usuario.Celular;

public class MonitoreoDeEstacionamientoTest {
	
	SEM sem;
	PuntoDeVenta kiosco;
	EntidadAdapter callCenter;
	String A;
	Celular cel;
	AppUsuario app;
	
	@Before
	public void setUp() throws Exception {
		
		// Set up
		sem = new SEM();
	    kiosco = new PuntoDeVenta();
	    kiosco.setSem(sem);
	    callCenter = new EntidadAdapter(sem);
	    callCenter.suscribirse();
	    cel= new Celular(app, 11000000, 120);
	    app= new AppUsuario(sem, "abc123", cel);
	    app.setHoraActual(10);
	    sem.setHoraActual(12);
	}
	
	@Test
	public void testSuscripciónGenérica() {
		// Exercise
		//datos de iniciar estacionamiento de punto de venta
		kiosco.iniciarEstacionamiento(A, 14, 15);
		// Verify
		assertTrue(callCenter.getInformes().size() == 1);
		assertTrue(callCenter.getInformes().contains("Estacionamiento iniciado a las 14hs. Y finalizado a las 15hs."));
		
		//datos de iniciar estacionamiento de app
		app.iniciarEstacionamiento();
		assertEquals(2,callCenter.getInformes().size());
		assertTrue(callCenter.getInformes().contains("Estacionamiento iniciado a las 10hs. Y finalizado a las 13hs."));
		
		//datos de finalizar estacionamiento de app
		app.finalizarEstacionamiento();
		assertEquals(3, callCenter.getInformes().size());
		assertTrue (callCenter.getInformes().contains("Hora de Inicio: 10hs. Hora de fin: 13hs. Duración: 3hs. Crédito restante: 0"));
		callCenter.desuscribirse();
	}

}
