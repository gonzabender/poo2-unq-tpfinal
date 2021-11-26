package tpfinal;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpfinal.*;
import tpfinal.app.usuario.FueraDeZona;
import tpfinal.app.usuario.NoEstaEstacionado;
import tpfinal.app.usuario.ValidezEstacionamiento;
import tpfinal.sistema.PuntoDeVenta;
import tpfinal.sistema.SEM;
import tpfinal.sistema.ZonaSem;
import tpfinal.usuario.AppUsuario;
import tpfinal.usuario.Celular;

public class AppUsuarioTest {

	private AppUsuario app;
	private Celular cel;
	private SEM sem;
	private String patente;
	private PuntoDeVenta kiosco;
	private ZonaSem posicion;
	
	@BeforeEach
	public void setUp() {
		
		sem= mock (SEM.class);
		cel= mock (Celular.class);
		app= new AppUsuario(sem,patente,cel);
		posicion= mock(ZonaSem.class);
		kiosco= mock (PuntoDeVenta.class);
		when(sem.consultarSaldo(cel)).thenReturn(0);
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
		ValidezEstacionamiento validez = mock(FueraDeZona.class);
		app.verificarEstacionamiento();
		
		assertEquals(validez, app.getValidezEstacionamiento());
		//verify(validez).verificar();
	}

	@Test
	public void testVerficarEstacionamientoCuandoEstaEnZonaPeroNoEstaEstacionadoDiceQueNoEstaEstacionado() {
		ValidezEstacionamiento validez = mock(NoEstaEstacionado.class);
		when(cel.setPosicion(posicion)).then(app.notificarGPS);
		app.verificarEstacionamiento();
		
		assertEquals(validez, app.getValidezEstacionamiento());
		//verify(validez).verificar();
	}

	@Test
	public void testVerficarEstacionamientoCuandoEstaEnZonaYEstaEstacionadoDiceQueEstaEstacionado() {
		ValidezEstacionamiento validez = mock(EstaEstacionado.class);
		when(cel.setPosicion(posicion)).then(app.notificarGPS);
		app.verificarEstacionamiento();
		app.iniciarEstacionamiento();
		
		assertEquals(validez, app.getValidezEstacionamiento());
		//verify(validez).verificar();
	}
	
	@Test
	public void testIniciarEstacionamientoCuandoNoEstaEnUnaZonaNoHaceNada() {
		
	}

	@Test
	public void testIniciarEstacionamientoCuandoEstaEnUnaZonaYNoEstaEstacionadoLoEstaciona() {
		
	}

	@Test
	public void testIniciarEstacionamientoCuandoEstaEnUnaZonaYEstaEstacionadoNoHaceNada() {
		
	}
		
	
	
	@AfterEach
	public void tearDown(){
		sem=null;
		cel= null;
		app= null;
		kiosco= null;
	}
	
	

	
}