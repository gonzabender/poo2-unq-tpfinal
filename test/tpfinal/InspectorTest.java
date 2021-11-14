package tpfinal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import tpfinal.*;

public class InspectorTest {
	
	AppUsuario app;
	Celular cel; 
	SEM sem;
	String patente;
	PuntoDeVenta kiosco;
	Inspector inspector;
	ZonaSem zona;
	
	
	@Before
	public void setUp() throws Exception {
		// set up
		
		sem= new SEM();
		cel= new Celular(app,54612315,0);
		app= new AppUsuario(sem,patente,cel);
		kiosco= new PuntoDeVenta();
		zona = new ZonaSem(sem, Arrays.asList(kiosco), inspector);
		inspector = new Inspector(sem, zona);
		kiosco.setSem(sem);
	
	}
	
	@Test
	public void testEstacionamientoVigente() {
		// Exercise
		kiosco.iniciarEstacionamiento(patente, 10, 18); // Alguien se estaciona
		
		// Verify
		assertTrue(inspector.verificarEstacionamiento(patente)); // Pasa el inspector, verifica que la patente se encuntra en el sistema
	}
	
	@Test
	public void testPatenteConInfracción() {
		// Verify
		assertFalse(inspector.verificarEstacionamiento(patente)); // Pasa el inspector, ve que la patente no se encuentra en el sistema
		assertTrue(sem.getInfracciones().size() == 1); //Realiza la infracción, la cual se guarda en el sistema
	}

}
