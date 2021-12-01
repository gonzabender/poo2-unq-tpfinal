package tpfinal.monitoreo;
/*package tpfinal;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tpfinal.*;
import tpfinal.app.usuario.AppUsuario;
import tpfinal.app.usuario.Celular;
import tpfinal.puntoDeVenta.PuntoDeVenta;
import tpfinal.sistema.EntidadAdapter;
import tpfinal.sistema.SEM;

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
	    app.setHoraActual(LocalTime.of(10, 0));
	    sem.setHoraActual(LocalTime.of(12, 0));
	}
	
	@Test
	public void testSuscripciónGenérica() {
		// Exercise
		//datos de iniciar estacionamiento de punto de venta
		
		kiosco.iniciarEstacionamiento(A, LocalTime.of(15, 0));
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

}*/
