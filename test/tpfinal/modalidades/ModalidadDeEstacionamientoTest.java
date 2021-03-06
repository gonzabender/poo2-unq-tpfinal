package tpfinal.modalidades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpfinal.app.usuario.AppUsuario;
import tpfinal.app.usuario.Celular;
import tpfinal.puntoDeVenta.PuntoDeVenta;
import tpfinal.sistema.SEM;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

public class ModalidadDeEstacionamientoTest {

	SEM sem;
	Celular iphone;
	AppUsuario app;
	PuntoDeVenta kiosco;
	LocalTime unaHora;

	@BeforeEach
	public void setUp() throws Exception {
		// Set up
		unaHora = LocalTime.of(10, 0);

		sem = new SEM();
		iphone = new Celular(app, 118594729, 0);
		app = new AppUsuario(sem, "PO2UNQ", iphone);
		kiosco = new PuntoDeVenta(sem);
		sem.setHoraActual(unaHora);
		app.setHoraActual(unaHora);
	}

	@Test
	public void testCargaCelular() {
		// Exercise
		kiosco.cargarCelular(iphone, 120);

		// Verify
		assertEquals(app.consultaSaldo(), 120); // El saldo se carg? al celular
		assertTrue(sem.getCompras().size() == 1); // La compra se agreg? al registro del sistema SEM

	}

	@Test
	public void testCompraPuntual() {
		// Exercise
		LocalTime horaSem = LocalTime.of(17, 0);
		sem.setHoraActual(horaSem);
		LocalTime fin = LocalTime.of(19, 0);
		kiosco.iniciarEstacionamiento("986DRH", fin); // Un estacionamiento cualquiera...

		// Verify
		assertTrue(sem.getEstacionamientosEnCurso().size() == 1); // Se registra en los estacionamientos en curso
		assertTrue(sem.getCompras().size() == 1); // Se registra en las compras de punto de venta
		assertTrue(sem.getEstacionamientosEnCurso().get(0).vigente()); // El estacionamiento se encuentra vigente

	}

	@Test
	public void testCompraPuntualFinaliza() {
		// Exercise
		LocalTime horaSem = LocalTime.of(14, 0);
		sem.setHoraActual(horaSem);
		LocalTime fin = LocalTime.of(17, 0);
		kiosco.iniciarEstacionamiento("986DRH", fin); // Un estacionamiento cualquiera...
		assertTrue(sem.getEstacionamientosEnCurso().size() == 1); // Se registra en los estacionamientos en curso
		assertTrue(sem.getCompras().size() == 1); // Se registra en las compras de punto de venta
		assertTrue(sem.getEstacionamientosEnCurso().get(0).vigente()); // El estacionamiento se encuentra vigente

		// Verify
		sem.setHoraActual(fin);
		assertTrue(sem.getEstacionamientosEnCurso().isEmpty()); // Al setear la nueva hora actual, el sistema elimina
																// los estacionamientos no vigentes.

	}

	@Test
	public void testCompraConApp() {
		// Exercise
		LocalTime horaFin = LocalTime.of(12, 0);
		kiosco.cargarCelular(iphone, 120);
		// Primero debe cargar cr?dito, aunque eso debe ser algo manual...
		app.iniciarEstacionamiento(); // Por el momento, no retorna nada
		app.setHoraActual(horaFin);
		sem.setHoraActual(horaFin);

		// Verify
		assertEquals(sem.getEstacionamientosEnCurso().size(), 1);
		app.finalizarEstacionamiento();
		assertEquals(sem.getEstacionamientosEnCurso().size(), 0);
	}

	@Test
	public void testCompraConAppFallida() {
		// Exercise
		app.iniciarEstacionamiento(); // Por el momento, no retorna nada

		String data = "No tiene credito suficiente para iniciar estacionamiento";
		// Verify
		assertEquals(0, app.consultaSaldo());
		assertEquals(sem.getEstacionamientosEnCurso().size(), 0);
		assertEquals(data, iphone.ultimaAlerta());
	}

}
