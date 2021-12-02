package tpfinal.monitoreo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

import tpfinal.app.usuario.AppUsuario;
import tpfinal.app.usuario.Celular;
import tpfinal.compras.estacionamientos.Estacionamiento;
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
	Estacionamiento estacionamiento;

	@Before
	public void setUp() throws Exception {

		// Set up
		sem = new SEM();
		callCenter = mock(EntidadAdapter.class);
		kiosco = new PuntoDeVenta();
		kiosco.setSem(sem);
		sem.setHoraActual(LocalTime.of(17, 0));
	}

	@Test
	public void testUnaEntidadSePuedeSuscribirAlSem() {
		sem.subscribirEntidad(callCenter);
		assertTrue(sem.getEntidades().contains(callCenter));
	}

	@Test
	public void testUnaEntidadSuscriptaPuedeDesuscribirse() {
		// Exercise
		sem.subscribirEntidad(callCenter);
		sem.desubscribirEntidad(callCenter);

		// Verify
		assertFalse(sem.getEntidades().contains(callCenter));
	}

	@Test
	public void testCuandoSeIniciaUnEstacionamientoLasEntidadesSonNotificadasDelInicioDelEstacionamiento() {
		// Exercise
		sem.subscribirEntidad(callCenter);
		sem.addEstacionamiento(estacionamiento);

		// Verify
		verify(callCenter).actualizarInicioEstacionamiento(estacionamiento);
	}

	@Test
	public void testCuandoSeFinalizaUnEstacionamientoLasEntidadesSonNotificadasDelFinalDelEstacionamiento() {
		// Exercise
		sem.subscribirEntidad(callCenter);
		kiosco.iniciarEstacionamiento("ACB123", LocalTime.of(19, 0));
		Estacionamiento e = sem.getEstacionamientosEnCurso().get(0);
		
		// Verify
		sem.setHoraActual(LocalTime.of(19, 0));
		verify(callCenter, times(1)).actualizarFinEstacionamiento(e);
	}

}