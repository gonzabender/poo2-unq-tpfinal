package tpfinal.app.usuario;

import static org.mockito.Mockito.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpfinal.puntoDeVenta.PuntoDeVenta;
import tpfinal.sistema.SEM;

/**
 * 
 * @author Lucas Alvarez
 *
 */

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
		app.setHoraActual(lasNueve);

		when(sem.iniciarEstacionamiento(cel, patente, app.getHoraActual())).thenReturn("Su estacionamiento es valido desde las "+ app.getHoraActual() +"hs. Hasta las 12:00hs.");
		when(sem.tieneSaldoSuficiente(cel)).thenReturn(true);
	}
	
	@Test
	public void testConsultaSaldoDaElSaldo() {
		assertEquals(120,app.consultaSaldo());
	}

	@Test
	public void testGetters() {
		assertEquals(sem,app.getSem());
		assertEquals(cel,app.getCelular());
		assertEquals(patente,app.getPatente());
		assertEquals(lasNueve,app.getHoraActual());
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
		app.iniciarEstacionamiento();
		
		assertEquals("Su estacionamiento es valido desde las 09:00hs. Hasta las 12:00hs.",cel.ultimaAlerta());
		assertEquals(EstadoEstacionamiento.EstaEstacionado, app.getEstadoEstacionamiento());
	
		verify(sem).iniciarEstacionamiento(cel, patente, lasNueve);
	}

	@Test
	public void testIniciarEstacionamientoSinCreditoSuficienteEnUnaZonaDeEstacionamientoNoPermiteEstacionar() {
		when(sem.tieneSaldoSuficiente(cel)).thenReturn(false);
		app.iniciarEstacionamiento();
		
		assertEquals("No tiene credito suficiente para iniciar estacionamiento",cel.ultimaAlerta());
		assertEquals(EstadoEstacionamiento.NoEstaEstacionado, app.getEstadoEstacionamiento());
		verify(sem, never()).iniciarEstacionamiento(cel, patente, lasNueve);
	}
	
	@Test
	public void testIniciarEstacionamientoCuandoEstaEnUnaZonaYEstaEstacionadoNoHaceNada() {
		cel.alerta("ya no hay mas alertas");
		app.iniciarEstacionamiento();
		app.iniciarEstacionamiento();
		
		assertEquals("No se puede iniciar estacionamiento porque ya esta estacionado",cel.ultimaAlerta());
		cel.descartarUltimaAlerta();
		cel.descartarUltimaAlerta();
		assertEquals("ya no hay mas alertas", cel.ultimaAlerta());
	}
	
	@Test
	public void testFinalizarEstacionamientoSinEstarEnUnaZonaNoHaceNada() {
		app.estaFueraDeZona();
		app.finalizarEstacionamiento();
		
		assertEquals("No se puede finalizar estacionamiento ya que no inicio uno",cel.ultimaAlerta());
	}
	
	@Test
	public void testFinalizarEstacionamientoSinEstarEstacionadoNoHaceNada() {
		app.estaDentroDeZona();
		app.finalizarEstacionamiento();
		
		assertEquals("No se puede finalizar estacionamiento ya que no inicio uno",cel.ultimaAlerta());
	}
	
	@Test
	public void testFinalizarEstacionamientoCuandoEstaEstacionadoFinalizaElEstacionamiento() {
		when(sem.finalizarEstacionamiento(cel)).thenReturn("Hora de Inicio: 09:00hs. Hora de fin: 12:00hs. Duración: 3hs. Crédito restante: 0");
		app.iniciarEstacionamiento();
		app.finalizarEstacionamiento();
		
		assertEquals("Hora de Inicio: 09:00hs. Hora de fin: 12:00hs. Duración: 3hs. Crédito restante: 0",cel.ultimaAlerta());
		verify(sem).finalizarEstacionamiento(cel);
	}
	
	@Test
	public void testCuandoSeCambiaDeConducirACaminarEnModoManualSinUnEstacionamientoDentroDeUnaZonaAlertaQueDeberiaIniciarEstacionamiento() {
		app.cambiarAManual();
		app.driving();
		app.walking();
		
		assertEquals("Aun no se ha estacionado", cel.ultimaAlerta());
	}
	
	@Test
	public void testCuandoSeCambiaDeCaminarAConducirEnModoManualConUnEstacionamientoDentroDeUnaZonaAlertaQueDeberiaFinalizrEstacionamiento() {
		app.cambiarAManual();
		app.walking();
		app.iniciarEstacionamiento();
		app.driving();
		
		assertEquals("Aun no se ha finalizado el estacionamiento", cel.ultimaAlerta());
	}
	
	@Test
	public void testCuandoSeCambiaDeConducirACaminarEnModoAutomaticoYNoTieneUnEstacionamientoLoEstacionaYLeAvisaQueLoEstacionoAdemasDeDarLaInformacionDelEstacionamiento() {
		app.cambiarAAutomatico();
		app.driving();
		app.walking();
		
		assertEquals("Se ha iniciado estacionamiento", cel.ultimaAlerta());
		cel.descartarUltimaAlerta();
		assertEquals("Su estacionamiento es valido desde las "+ app.getHoraActual() +"hs. Hasta las 12:00hs.", cel.ultimaAlerta());
		verify(sem).iniciarEstacionamiento(cel, patente, lasNueve);
	}
	
	@Test
	public void testCuandoSeCambiaDeConducirACaminarEnModoAutomaticoNoTieneUnEstacionamientoYNoTieneSaldoSuficienteAvisaQueInicioEstacionamientoYQueNoTieneSaldoSuficiente() {
		when(sem.tieneSaldoSuficiente(cel)).thenReturn(false);
		
		app.cambiarAAutomatico();
		app.driving();
		app.walking();
		
		assertEquals("Se ha iniciado estacionamiento", cel.ultimaAlerta());
		cel.descartarUltimaAlerta();
		assertEquals("No tiene credito suficiente para iniciar estacionamiento", cel.ultimaAlerta());
		verify(sem,never()).iniciarEstacionamiento(cel, patente, lasNueve);
	}
	
	@Test
	public void testCuandoSeCambiaDeCaminarAConducirEnModoAutomaticoTieneUnEstacionamientoTerminaElEstacionamientoYLeAvisaQueTerminoAdemasDeDarLaInformacionDelEstacionamiento() {
		when(sem.finalizarEstacionamiento(cel)).thenReturn("Hora de Inicio: 09:00hs. Hora de fin: 12:00hs. Duración: 3hs. Crédito restante: 0");
		
		app.cambiarAAutomatico();
		app.driving();
		app.walking();
		app.driving();
		
		assertEquals("Se ha finalizado estacionamiento", cel.ultimaAlerta());
		cel.descartarUltimaAlerta();
		assertEquals("Hora de Inicio: 09:00hs. Hora de fin: 12:00hs. Duración: 3hs. Crédito restante: 0", cel.ultimaAlerta());
		verify(sem).iniciarEstacionamiento(cel, patente, lasNueve);
		verify(sem).finalizarEstacionamiento(cel);
	}
	
	@Test
	public void testCuandoRepetidasVecesSeLeAvisaALaAppQueEstaConduciendoOCaminandoSoloAvisaLaPrimeraVezQueSeLeAvisaDeAlgoSiAntesHaciaLoContrario() {
		cel.alerta("primera alerta");
		app.cambiarAManual();
		app.walking();
		app.walking();
		app.walking();
		app.walking();
		app.walking();
		app.walking();
		
		assertEquals("Aun no se ha estacionado",cel.ultimaAlerta());
		cel.descartarUltimaAlerta();
		assertEquals("primera alerta",cel.ultimaAlerta());
	}
	
	@Test
	public void testCuandoSeDesactivaElSensorDeMovimientoElModoManualDeLaAppDejaDeEnviarAlertasYSePuedeReactivarParaPoderRecibirOtraVezLasAlertas() {
		app.desactivarMoveS();
		cel.alerta("primera alerta");
		app.driving();
		app.walking();
		app.driving();
		
		assertEquals("primera alerta", cel.ultimaAlerta());
		
		app.activarMoveS();
		app.walking();
		
		assertEquals("Aun no se ha estacionado", cel.ultimaAlerta());
	}
	
	@Test
	public void testCambiarDeCaminarAManejarODeManejarACaminarFueraDeUnaZonaNoHaceNada() {
		cel.alerta("primera alerta");
		app.estaFueraDeZona();
		app.driving();
		app.walking();
		app.driving();
		app.walking();
		
		assertEquals("primera alerta",cel.ultimaAlerta());
	}
	
	@Test
	public void testCambiarACaminarCuandoEstaEstacionadoNoHaceNada() {
		cel.alerta("primera alerta");
		app.driving();
		app.iniciarEstacionamiento();
		app.walking();
		
		cel.descartarUltimaAlerta(); //descarta la alerta con la informacion del estacionamiento
		assertEquals("primera alerta", cel.ultimaAlerta());
	}
	
	@Test
	public void testCambiarAManejarCuandoNoEstaEstacionadoNoHaceNada() {
		app.walking();
		cel.alerta("antes de cambiar a manejar");
		app.driving();
		
		assertEquals("antes de cambiar a manejar", cel.ultimaAlerta());
	}
	
	
	@AfterEach
	public void tearDown(){
		sem=null;
		cel= null;
		app= null;
		kiosco= null;
	}	
}