package tpfinal.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import tpfinal.*;

public class MonitoreoDeEstacionamientoTest {
	
	SEM sem;
	PuntoDeVenta kiosco;
	EntidadAdapter callCenter;
	String A;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		// Set up
		sem = new SEM();
	    kiosco = new PuntoDeVenta();
	    callCenter = new EntidadAdapter();
	    callCenter.suscribirse();
	}
	
	@Test
	public void testSuscripciónGenérica() {
		// Exercise
		kiosco.iniciarEstacionamiento(A, 14, 15);
		
		// Verify
		assertTrue(callCenter.getInteresantes().size() == 1);
		
	}

}
