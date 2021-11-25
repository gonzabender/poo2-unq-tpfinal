package tpfinal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpfinal.*;
import tpfinal.sistema.PuntoDeVenta;
import tpfinal.sistema.SEM;
import tpfinal.usuario.AppUsuario;
import tpfinal.usuario.Celular;

public class AppUsuarioTest {

	private AppUsuario app;
	private Celular cel;
	private SEM sem;
	private String patente;
	private PuntoDeVenta kiosco;
	
	@BeforeEach
	public void setUp() {
		
		sem= new SEM();
		cel= new Celular(app,54612315,0);
		app= new AppUsuario(sem,patente,cel);
		kiosco= new PuntoDeVenta();
		kiosco.setSem(sem);
	
	}
	
	@Test
	public void testConsultaSaldo() {
		assertEquals(0,app.consultaSaldo());
		kiosco.cargarCelular(cel, 1000);;
		assertEquals(1000, app.consultaSaldo());
	}

	
}
