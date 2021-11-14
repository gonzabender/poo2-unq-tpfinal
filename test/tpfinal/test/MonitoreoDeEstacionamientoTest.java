package tpfinal.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import tpfinal.*;

public class MonitoreoDeEstacionamientoTest {
	
	SEM sem;
	PuntoDeVenta kiosco;
	EntidadAdapter callCenter;
	String A;
	
	@Before
	public void setUp() throws Exception {
		
		// Set up
		sem = new SEM();
	    kiosco = new PuntoDeVenta();
	    kiosco.setSem(sem);
	    callCenter = new EntidadAdapter(sem);
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
