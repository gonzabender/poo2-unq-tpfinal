package tpfinal.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpfinal.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ModalidadDeEstacionamientoTest {

	SEM sem;
	Celular iphone;
	AppUsuario app;
	PuntoDeVenta kiosco;

	@BeforeEach
	public void setUp() throws Exception {
		// Set up

		sem = new SEM();
		iphone = new Celular(app, 118594729, 0);
		app = new AppUsuario(sem, "PO2UNQ", iphone);
		kiosco = new PuntoDeVenta();
		kiosco.setSem(sem);

	}

	@Test
	public void testCargaCelular() {
		// Exercise
		kiosco.cargarCelular(iphone, 120);

		// Verify
		assertEquals(iphone.getCrédito(), 120); // El saldo se cargó al celular
		assertTrue(sem.getCompras().size() == 1); // La compra se agregó al registro del sistema SEM

	}

	@Test
	public void testCompraPuntual() {
		// Exercise
		kiosco.iniciarEstacionamiento("986DRH", 10, 15); // Un estacionamiento cualquiera...

		// Verify
		assertTrue(sem.getEstacionamientosEnCurso().size() == 1); // Se registra en los estacionamientos en curso
		assertTrue(sem.getCompras().size() == 1); // Se registra en las compras de punto de venta
		assertTrue(sem.getEstacionamientosEnCurso().get(0).vigente()); // El estacionamiento se encuentra vigente

	}
	
	@Test
	public void testCompraConApp() {
		// Exercise
		app.cargarCredito(kiosco, 120); // Primero debe cargar crédito, aunque eso debe ser algo manual...
		app.iniciarEstacionamiento(); // Por el momento, no retorna nada 
		
		// Verify
		assertEquals(sem.getEstacionamientosEnCurso().size(), 1);
		app.finalizarEstacionamiento();
		assertEquals(sem.getEstacionamientosEnCurso().size(), 0);
	}
	
	@Test
	public void testCompraConAppFallida() {
		// Exercise
        app.iniciarEstacionamiento(); // Por el momento, no retorna nada 
		
		// Verify
        assertEquals(0, app.consultaSaldo());
		assertEquals(sem.getEstacionamientosEnCurso().size(), 0);
	}

}
