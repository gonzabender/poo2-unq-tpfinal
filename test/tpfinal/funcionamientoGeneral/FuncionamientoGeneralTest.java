package tpfinal.funcionamientoGeneral;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpfinal.app.usuario.AppUsuario;
import tpfinal.app.usuario.Celular;
import tpfinal.inspector.Inspector;
import tpfinal.puntoDeVenta.PuntoDeVenta;
import tpfinal.sistema.SEM;
import tpfinal.sistema.ZonaSem;

/**
 * 
 * @author Ignacio Cervantes
 *
 */

public class FuncionamientoGeneralTest {

	/**
	 * Esta clase prueba como funciona la aplicación con varios estacionamientos a
	 * medida que transcurre un día normal.
	 */

	SEM sem;
	Inspector inspector1;
	Inspector inspector2;
	ZonaSem zona1;
	ZonaSem zona2;
	PuntoDeVenta pv1;
	PuntoDeVenta pv2;
	PuntoDeVenta pv3;
	PuntoDeVenta pv4;
	Celular cel1;
	Celular cel2;
	Celular cel3;
	Celular cel4;
	AppUsuario app1;
	AppUsuario app2;
	AppUsuario app3;
	AppUsuario app4;
	LocalTime inicio;
	LocalTime fin;

	@BeforeEach
	public void setUp() throws Exception {
		// Set up

		inicio = LocalTime.of(7, 0);
		fin = LocalTime.of(20, 0);
		sem = new SEM();
		pv1 = new PuntoDeVenta(sem);
		pv2 = new PuntoDeVenta(sem);
		pv3 = new PuntoDeVenta(sem);
		pv4 = new PuntoDeVenta(sem);
		zona1 = new ZonaSem(sem, Arrays.asList(pv1, pv2), inspector1);
		zona2 = new ZonaSem(sem, Arrays.asList(pv3, pv4), inspector2);
		inspector1 = new Inspector(sem, zona1);
		inspector2 = new Inspector(sem, zona2);
		cel1 = new Celular(app1, 1175472837, 0);
		cel2 = new Celular(app2, 118746538, 0);
		cel3 = new Celular(app3, 223467591, 0);
		cel4 = mock(Celular.class);
		app1 = new AppUsuario(sem, "PO2UNQ", cel1);
		app2 = new AppUsuario(sem, "ABC123", cel2);
		app3 = new AppUsuario(sem, "DEF456", cel3);
		app4 = new AppUsuario(sem, "FGH789", cel4);
		sem.setHoraActual(inicio);
		pv1.cargarCelular(cel1, 120);
		pv2.cargarCelular(cel2, 5000);
		pv3.cargarCelular(cel3, 400);
		pv4.iniciarEstacionamiento("VVT591", LocalTime.of(15, 0));
		app1.iniciarEstacionamiento();
		app2.iniciarEstacionamiento();
		app3.iniciarEstacionamiento();
	}

	@Test
	public void testSistema() {
		assertTrue(sem.getZonas().equals(Arrays.asList(zona1, zona2))); // El sistema conoce sus zonas
		assertTrue(zona1.getPuntosDeVenta().equals(Arrays.asList(pv1, pv2)));
		assertTrue(zona2.getPuntosDeVenta().equals(Arrays.asList(pv3, pv4))); // Las zonas conocen sus puntos de venta
		assertEquals(sem.getCompras().size(), 4);
	}

	@Test
	public void testPrimerasHoras() {
		// Exercise
		when(cel4.getSaldo()).thenReturn(0);
		app4.iniciarEstacionamiento();
		String info = "No tiene credito suficiente para iniciar estacionamiento";

		// Verify
		assertEquals(sem.getEstacionamientosEnCurso().size(), 4); // Estacionan 4 de 5, uno falla.
		verify(cel4, times(1)).alerta(info); // Le envía la información de que no posee el saldo suficiente.
	}

	@Test
	public void testMediodia() {
		sem.setHoraActual(LocalTime.of(12, 0));
		app1.finalizarEstacionamiento();
		assertEquals(sem.getEstacionamientosEnCurso().size(), 3);

		pv2.iniciarEstacionamiento("GET396", LocalTime.of(13, 0));
		assertEquals(sem.getEstacionamientosEnCurso().size(), 4);

		sem.setHoraActual(LocalTime.of(13, 0));
		sem.setHoraActual(LocalTime.of(15, 0));
		assertEquals(sem.getEstacionamientosEnCurso().size(), 2); // Solo quedan cel2 y cel3

		assertFalse(inspector1.verificarEstacionamiento("GET396"));
		assertTrue(!sem.getInfracciones().isEmpty()); // Sigue estacionado a pesar de Haber finalizado su
														// estacionamiento medido.
	}

	@Test
	public void testTarde() {
		sem.setHoraActual(LocalTime.of(12, 0));
		app1.finalizarEstacionamiento();
		sem.setHoraActual(LocalTime.of(15, 0));
		sem.setHoraActual(LocalTime.of(17, 0));
		app3.finalizarEstacionamiento();
		pv1.iniciarEstacionamiento("JBW861", fin);
		pv4.iniciarEstacionamiento("JWW525", LocalTime.of(18, 0));
		assertEquals(sem.getEstacionamientosEnCurso().size(), 3);

		sem.setHoraActual(LocalTime.of(18, 0));
		assertEquals(sem.getEstacionamientosEnCurso().size(), 2);

		sem.setHoraActual(fin);
		assertEquals(sem.getEstacionamientosEnCurso().size(), 0); // A las 20 finaliza todo automáticamente
		String info = "No es requerido estacionar entre las 20:00hs y las 07:00hs";
		sem.setHoraActual(LocalTime.of(23, 0));
		when(cel4.getSaldo()).thenReturn(90);
		app4.iniciarEstacionamiento();
		verify(cel4, times(1)).alerta(info);

	}

}
