package tpfinal.app.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Lucas Alvarez
 *
 */
public class CelularTest {
	
	private Celular cel;
	private AppUsuario app;
	
	@BeforeEach
	public void setup() {
		app= mock(AppUsuario.class);
		cel= new Celular(app, 1120304050, 0);
	}
	
	@Test
	public void testUnCelularPuedeGuardarLasAlertasQueLeLleganYMostrarLaUltima() {
		this.cel.alerta("alerta de prueba");
		assertEquals("alerta de prueba", cel.ultimaAlerta());
	}
	
	@Test
	public void testUnCelularPuedeDescartarLaUltimaAlertaParaMostrarLaProxima() {
		this.cel.alerta("alerta de prueba");
		this.cel.alerta("alerta que se va a ver");
		this.cel.alerta("alerta que se va a descartar");
		
		assertEquals("alerta que se va a descartar",cel.ultimaAlerta());
		cel.descartarUltimaAlerta();
		assertEquals("alerta que se va a ver",cel.ultimaAlerta());
	}
	
	@Test
	public void cargarCreditoCargaCredito() {
		assertEquals(0, cel.getSaldo());
		cel.cargarSaldo(100);
		assertEquals(100,cel.getSaldo());
	}
	
	@Test
	public void testDescontarSaldoDescuentaSaldo() {
		cel.cargarSaldo(135);
		assertEquals(135,cel.getSaldo());
		cel.restarSaldo(40);
		assertEquals(95,cel.getSaldo());
	}
	
	@Test
	public void testUnCelularTieneUnNumero() {
		assertEquals(1120304050,cel.getNúmero());
	}
}
