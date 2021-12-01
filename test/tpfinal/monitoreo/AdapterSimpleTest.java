package tpfinal.monitoreo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import tpfinal.app.usuario.Celular;
import tpfinal.compras.estacionamientos.Estacionamiento;
import tpfinal.compras.estacionamientos.EstacionamientoCompraPuntual;
import tpfinal.sistema.Entidad;
import tpfinal.sistema.EntidadAdapter;
import tpfinal.sistema.SEM;

public class AdapterSimpleTest {

	SEM sem;
	EntidadAdapter adapter;
	Entidad entidad;
	Estacionamiento e;
	Celular cel;

	@Before
	public void setUp() throws Exception {
		sem = new SEM();
		sem.setHoraActual(LocalTime.now());
		adapter = new EntidadAdapter(sem);
		entidad = mock(Entidad.class);
		adapter.suscribirse();
		e = mock(Estacionamiento.class);
		cel = mock(Celular.class);
	}

	@Test
	public void testNotificar() {
		String i = sem.iniciarEstacionamiento(cel, "ABC123", LocalTime.now());
		when(entidad.notificar(i)).thenReturn(i);

		verify(entidad, times(1)).notificar(i);
	}
}
