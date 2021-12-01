package tpfinal.modalidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpfinal.app.usuario.AppUsuario;
import tpfinal.app.usuario.Celular;
import tpfinal.puntoDeVenta.PuntoDeVenta;
import tpfinal.sistema.SEM;

public class FinalizarTodosTest {

	SEM sem;
	Celular cel1;
	Celular cel2;
	Celular cel3;
	Celular cel4;
	AppUsuario app;
	AppUsuario app2;
	AppUsuario app3;
	AppUsuario app4;
	PuntoDeVenta kiosco;
	LocalTime unaHora;

	@BeforeEach
	public void setUp() throws Exception {
		// Set up
		unaHora = LocalTime.of(10, 0);
		sem = new SEM();
		cel1 = mock(Celular.class);
		cel2 = mock(Celular.class);
		cel3 = mock(Celular.class);
		cel4 = mock(Celular.class);
		app = new AppUsuario(sem, "PO2UNQ", cel1);
		app2 = new AppUsuario(sem, "ABC123", cel2);
		app3 = new AppUsuario(sem, "DEF456", cel3);
		app4 = new AppUsuario(sem, "FGH789", cel4);
		kiosco = new PuntoDeVenta();
		kiosco.setSem(sem);
		sem.setHoraActual(unaHora);
		app.setHoraActual(unaHora);
		when(cel1.getSaldo()).thenReturn(400);
		when(cel2.getSaldo()).thenReturn(400);
		when(cel3.getSaldo()).thenReturn(400);
		when(cel4.getSaldo()).thenReturn(400);
		app.iniciarEstacionamiento();
		app2.iniciarEstacionamiento();
		app3.iniciarEstacionamiento();
		app4.iniciarEstacionamiento();
	}

	@Test
	public void testFinalizarTodos() {
		// Exercise
		sem.setHoraActual(LocalTime.of(20, 0));
		String info = "Su estacionamiento medido fue finalizado por haber alcanzado la hora límite del servicio";
		sem.finalizarTodosLosEstacionamientos();

		// Verify
		verify(cel1, times(1)).restarSaldo(400); // El sistema resta el equivalente a 10hs (actualmente = 40 * 10)
		verify(cel1, times(1)).alerta(info);
		verify(cel2, times(1)).restarSaldo(400);
		verify(cel2, times(1)).alerta(info);
		verify(cel3, times(1)).restarSaldo(400);
		verify(cel3, times(1)).alerta(info);
		verify(cel4, times(1)).restarSaldo(400);
		verify(cel4, times(1)).alerta(info);
	}

}
