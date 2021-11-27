package tpfinal.app.usuario;

import static org.mockito.Mockito.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpfinal.sistema.PuntoDeVenta;
import tpfinal.sistema.SEM;

public class AppUsuarioTest {

	private AppUsuario app;
	private Celular cel;
	private SEM sem;
	private String patente;
	private PuntoDeVenta kiosco;
	LocalTime lasNueve;

	
	@BeforeEach
	public void setUp() {
		lasNueve=LocalTime.of(9, 0, 0);
		sem= mock (SEM.class);
		cel= new Celular(app, 0, 120);
		app= new AppUsuario(sem,patente,cel);
		kiosco= mock (PuntoDeVenta.class);
		when(sem.consultarSaldo(cel)).thenReturn(0);
		app.setHoraActual(lasNueve);

	}
	
	@Test
	public void testConsultaSaldoAlInicioEs0() {
		assertEquals(0,app.consultaSaldo());
		verify(sem).consultarSaldo(cel);
	}
	
	@Test
	public void testCargarSaldo() {
		when(sem.consultarSaldo(cel)).thenReturn(1253);
		kiosco.cargarCelular(cel, 1253);
		
		assertEquals(1253,app.consultaSaldo());
		verify(kiosco).cargarCelular(cel, 1253);
	}
	
	@Test
	public void testVerficarValidezEstacionamientoCuandoNoEstaEnZonaDiceQueNoEstaEnUnaZona() {
		app.estaFueraDeZona();
		
		assertEquals(EstadoEstacionamiento.NoEstaEnZona, app.getEstadoEstacionamiento());
	}

	@Test
	public void testPorDefectoUnaAolicacionEstaDentroDeUnaZonaYNoEstaEstacionado() {
		assertEquals(EstadoEstacionamiento.NoEstaEstacionado, app.getEstadoEstacionamiento());
	}
	
	@Test
	public void testVerficarEstacionamientoCuandoElCelularEntraEnUnaZonaYLuegoSaleDiceQueNoEstaEnUnaZona() {
		app.estaFueraDeZona();
		app.estaDentroDeZona();
		app.estaFueraDeZona();
		
		assertEquals(EstadoEstacionamiento.NoEstaEnZona, app.getEstadoEstacionamiento());
	}

	@Test
	public void testVerficarEstacionamientoCuandoEstaEnZonaYEstacionoDiceQueEstaEstacionado() {
		when(sem.tieneSaldoSuficiente(cel)).thenReturn(true);
		assertEquals(EstadoEstacionamiento.NoEstaEstacionado, app.getEstadoEstacionamiento());
		app.iniciarEstacionamiento();
		app.iniciarEstacionamiento();
		
		assertEquals(EstadoEstacionamiento.EstaEstacionado, app.getEstadoEstacionamiento());
		assertEquals("No se puede iniciar estacionamiento porque ya esta estacionado",cel.ultimaAlerta());
	}
	
	@Test
	public void testIniciarEstacionamientoCuandoNoEstaEnUnaZonaNoHaceNada() {
		app.estaFueraDeZona();
		app.iniciarEstacionamiento();
		
		assertEquals("No puede iniciar estacionamiento porque no se encuentra en una zona de estacionamiento medido",cel.ultimaAlerta());
		assertEquals(EstadoEstacionamiento.NoEstaEnZona, app.getEstadoEstacionamiento());
		verify(sem, never()).iniciarEstacionamiento(cel, patente, lasNueve);
	}

	@Test
	public void testIniciarEstacionamientoCuandoEstaEnUnaZonaYNoEstaEstacionadoLoEstacionaSiTieneSuficienteSaldo() {
		when(sem.iniciarEstacionamiento(cel, patente, app.getHoraActual())).thenReturn("Su estacionamiento es valido desde las "+ app.getHoraActual() +"hs. Hasta las 12:00hs.");
		when(sem.tieneSaldoSuficiente(cel)).thenReturn(true);
		app.iniciarEstacionamiento();
		
		assertEquals("Su estacionamiento es valido desde las 09:00hs. Hasta las 12:00hs.",cel.ultimaAlerta());
		assertEquals(EstadoEstacionamiento.EstaEstacionado, app.getEstadoEstacionamiento());
	
		verify(sem).iniciarEstacionamiento(cel, patente, lasNueve);
	}

	@Test
	public void testIniciarEstacionamientoSinCreditoSuficienteEnUnaZonaDeEstacionamientoNoPermiteEstacionar() {
		when(sem.iniciarEstacionamiento(cel, patente, app.getHoraActual())).thenReturn("Su estacionamiento es valido desde las "+ app.getHoraActual() +"hs. Hasta las 12:00hs.");
		when(sem.tieneSaldoSuficiente(cel)).thenReturn(false);
		app.iniciarEstacionamiento();
		
		assertEquals("No tiene credito suficiente para iniciar estacionamiento",cel.ultimaAlerta());
		assertEquals(EstadoEstacionamiento.NoEstaEstacionado, app.getEstadoEstacionamiento());
		verify(sem, never()).iniciarEstacionamiento(cel, patente, lasNueve);
	}
	
	@Test
	public void testIniciarEstacionamientoCuandoEstaEnUnaZonaYEstaEstacionadoNoHaceNada() {
		cel.alerta("ya no hay mas alertas");
		when(sem.tieneSaldoSuficiente(cel)).thenReturn(true);
		when(sem.iniciarEstacionamiento(cel, patente, app.getHoraActual())).thenReturn("Su estacionamiento es valido desde las "+ app.getHoraActual() +"hs. Hasta las 12:00hs.");
		app.iniciarEstacionamiento();
		app.iniciarEstacionamiento();
		
		assertEquals("No se puede iniciar estacionamiento porque ya esta estacionado",cel.ultimaAlerta());
		cel.descartarUltimaAlerta();
		cel.descartarUltimaAlerta();
		assertEquals("ya no hay mas alertas", cel.ultimaAlerta());
	}
	
	@Test
	public void testCuandoSeCambiaDeConducirACaminarEnModoManualSinUnEstacionamientoDentroDeUnaZonaAlertaQueDeberiaIniciarEstacionamiento() {
		app.cambiarAManual();
		app.driving();
		app.walking();
		
		assertEquals("Deberia iniciar estacionamiento", cel.ultimaAlerta());
	}
	
	
	@AfterEach
	public void tearDown(){
		sem=null;
		cel= null;
		app= null;
		kiosco= null;
	}	
}