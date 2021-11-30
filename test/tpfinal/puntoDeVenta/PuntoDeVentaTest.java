package tpfinal.puntoDeVenta;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpfinal.app.usuario.Celular;
import tpfinal.compras.Compra;
import tpfinal.sistema.SEM;

public class PuntoDeVentaTest {

	
	PuntoDeVenta pv;
	SEM sem;
	String patente;
	Celular cel;
	LocalTime algunFinal;
	
	@BeforeEach
	public void setUp() {
		cel=mock (Celular.class);
		patente= "ASD123";
		sem = new SEM();
		pv= new PuntoDeVenta();
		pv.setSem(sem);
		algunFinal= LocalTime.of(18, 0);
	}
	
	@Test
	public void testCargarCelularCargaElCelularYRegistraLaVenta() {
		pv.cargarCelular(cel, 100);
		
		verify(cel).cargarSaldo(100);
		assertTrue(sem.getCompras().size() == 1);
	}

	@Test
	public void testIniciarEstacionamientoEnUnaCompraPuntualLoInicia() {
		pv.iniciarEstacionamiento(patente, algunFinal);;
		
		assertTrue(sem.getCompras().size() == 1);
		assertTrue(sem.getEstacionamientosEnCurso().size() == 1);
	}

}
