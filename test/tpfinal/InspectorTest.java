package tpfinal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import tpfinal.*;
import tpfinal.app.usuario.AppUsuario;
import tpfinal.app.usuario.Celular;
import tpfinal.inspector.Inspector;
import tpfinal.puntoDeVenta.PuntoDeVenta;
import tpfinal.sistema.SEM;
import tpfinal.sistema.ZonaSem;

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
		sem.setHoraActual(LocalTime.of(10, 0));
		LocalTime fin = LocalTime.of(18, 0);
		kiosco.iniciarEstacionamiento(patente, fin); // Alguien se estaciona
		
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
