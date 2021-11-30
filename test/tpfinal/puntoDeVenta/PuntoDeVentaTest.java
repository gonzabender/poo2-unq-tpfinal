package tpfinal.puntoDeVenta;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpfinal.app.usuario.Celular;
import tpfinal.compras.estacionamientos.Estacionamiento;
import tpfinal.sistema.SEM;

public class PuntoDeVentaTest {

	/**
	 * En este test, comprobamos que un punto de venta puede cargar un celular,
	 * iniciar un estacionamiento, notificar al sistema que se realizo una compra y
	 * tambien verificar la hora en la que finaliza el estacionamiento comprado.
	 */

	PuntoDeVenta pv;
	SEM sem;
	String patente;
	Celular cel;
	LocalTime algunFinal;

	@BeforeEach
	public void setUp() {
		// Set up
		cel = mock(Celular.class);
		patente = "ASD123";
		sem = new SEM();
		pv = new PuntoDeVenta();
		pv.setSem(sem);
		algunFinal = LocalTime.of(18, 0);
	}

	@Test
	public void testCargarCelularCargaElCelularYRegistraLaVenta() {
		// Exercise
		pv.cargarCelular(cel, 100);

		// Verify
		verify(cel).cargarSaldo(100);
		assertTrue(sem.getCompras().size() == 1);
	}

	@Test
	public void testIniciarEstacionamientoEnUnaCompraPuntualLoInicia() {
		// Exercise
		pv.iniciarEstacionamiento(patente, algunFinal);

		// Verify 1
		assertTrue(sem.getCompras().size() == 1);
		assertTrue(sem.getEstacionamientosEnCurso().size() == 1);

		// Verify 2
		Estacionamiento e = sem.getEstacionamientosEnCurso().get(0);
		assertTrue(e.getHorarioFin().equals(algunFinal));
	}

}
