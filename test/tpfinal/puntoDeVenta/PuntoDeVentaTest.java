package tpfinal.puntoDeVenta;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpfinal.app.usuario.Celular;
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
		sem=mock(SEM.class);
		pv= new PuntoDeVenta();
		pv.setSem(sem);
		algunFinal= LocalTime.of(18, 0);
	}
	
	@Test
	public void testCargarCelularCargaElCelularYRegistraLaVenta() {
		pv.cargarCelular(cel, 100);
		
		verify(cel).cargarSaldo(100);
		verify(sem).addCompra(null);//no se que hacer
	}

	@Test
	public void testIniciarEstacionamientoEnUnaCompraPuntualLoInicia() {
		pv.iniciarEstacionamiento(patente, algunFinal);;
		
		verify(sem).addCompra(null);//no se que hacer
		verify(sem).addEstacionamiento(null);//no se que hacer
	}

}
