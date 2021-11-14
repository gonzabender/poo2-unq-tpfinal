package tpfinal.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tpfinal.*;

public class AutomatizacionAppTest {

	SEM sem;
	Celular cel;
	AppUsuario app;
	ZonaSem posicion;
	PuntoDeVenta pv;
	ArrayList <PuntoDeVenta> pvs= new ArrayList <>();
	Inspector inspector;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	
	@Before
	public void setUp() {
		sem= new SEM();
		cel= new Celular(app, 1122334455, 0);
		app= new AppUsuario(sem, "abc123", cel);
		app.setHoraActual(8);
		pv= new PuntoDeVenta();
		pv.setSem(sem);
		pvs.add(pv);
		inspector=new Inspector(sem, posicion);
		
		app.cargarCredito(pv, 120);
		
		posicion= new ZonaSem(sem, pvs, inspector);
		app.setPosicion(posicion);
		
		System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void testAlertasDeManejoModoManual() {
		//manejar cuando no esta estacionado no alerta y repeticion de mensaje driving 
		app.driving();
		app.driving();
		app.driving();
		String data= "";//data en estos test va a representar lo proximo que se imprime en pantalla
		assertEquals(data, outContent.toString());
		
		//repeticion de mensaje walking y alerta de no haberse estacionado
		data= "Aun no se ha estacionado";
		app.walking();
		assertEquals(data, outContent.toString());
		app.walking();
		app.walking();
		assertEquals(data, outContent.toString());
		
		//alerta de no haber finalizado estacionamiento
		app.iniciarEstacionamiento();
		app.driving();
		data= data + "Su estacionamiento es valido desde las 8hs. Hasta las 11hs."
					+"Aun no ha finalizado el estacionamiento";
		assertEquals(data, outContent.toString());
		
		//no hay alerta al iniciar el estacionamiento y seguir caminando
		app.walking();
		app.walking();
		assertEquals(data,outContent.toString());
		
		//no hay alerta al finalizar el estacionamiento y seguir manejando
		app.finalizarEstacionamiento();
		app.driving();
		data= data + "Hora de Inicio: 8hs. Hora de fin: 11hs. Duración: 3hs. Crédito restante: 0";
		assertEquals(data, outContent.toString());
		
	}
	
	@Test
	public void testAlertasModoAutomatico() {
		//cambia su estado a modo automatico
		app.cambiarModo();
		/*assertEquals(Automatico.class, app.estado.class);*/
		
		//manejar cuando no esta estacionado no alerta y repeticion de mensaje driving 
		app.driving();
		app.driving();
		app.driving();
		String data= "";
		assertEquals(data, outContent.toString());		
		
		//en modo automatico el usuario no puede iniciar o finalizar estacionamientos manualmente
		app.iniciarEstacionamiento();
		data= data + "No se puede iniciar estacionamiento en modo automatico";
		assertEquals(data, outContent.toString());
		app.finalizarEstacionamiento();
		data= data + "No se puede finalizar estacionamiento en modo automatico";
		assertEquals(data, outContent.toString());
		
		//repeticion de mensaje walking y alerta de estacionamiento automatico
		data= data + "Se ha estacionado. Su estacionamiento es valido desde las 8hs. Hasta las 11hs.";
		app.walking();
		assertEquals(data, outContent.toString());
		app.walking();
		app.walking();
		assertEquals(data, outContent.toString());

		//alerta de finalizacion de estacionamiento automatico
		app.driving();
		data= data + "Se ha finalizado el estacionamiento. Hora de Inicio: 8hs. Hora de fin: 11hs. Duración: 3hs. Crédito restante: 0";
		assertEquals(data, outContent.toString());
	}
	
	@Test
	public void testToggleMovementSensor() {
		//toggle cuando esta en modo manual funciona sin inconvenientes
		String data= "";
		app.toggleMovementSensor();//MovementSensor desactivado
		assertEquals(data, outContent.toString());
		app.toggleMovementSensor();//MovementSensor activado
		assertEquals(data,outContent.toString());
		
		//no se puede cambiar a modo automatico cuando el MovementSensor esta desactivado
		app.toggleMovementSensor();//desactivado
		app.cambiarModo();
		data= "El sensor de movimiento esta desactivado, por lo cual no se puede cambiar a modo automatico";
		assertEquals(data, outContent.toString());
		
		//si se desactiva el MovementSensor con la app en modo automatico, el modo automatico no puede funcionar
		//por lo que se cambia a modo manual con el sensor desactivado
		app.toggleMovementSensor();//activado
		app.cambiarModo();
		app.toggleMovementSensor();
		data = data + "Se ha desactivado el modo automatico porque se desactivo el sensor de movimiento";
		assertEquals(data, outContent.toString());	
	}
	
	@Test
	public void testMovementSensorDesactivadoNoAlertaPorMovimiento() {
		app.toggleMovementSensor();//MovementSensor desactivado
		app.driving();
		app.walking();
		app.driving();
		app.walking();
		assertEquals("", outContent.toString());
	}
	
	@Test
	public void testMovementSensorDesactivadoIniciaYFinaliza() {
		app.toggleMovementSensor();
		//alerta de no haber finalizado estacionamiento
		app.iniciarEstacionamiento();
		String data= "Su estacionamiento es valido desde las 8hs. Hasta las 11hs.";
		assertEquals(data, outContent.toString());
				
		//no hay alerta al finalizar el estacionamiento y seguir manejando
		app.finalizarEstacionamiento();
		data= data + "Hora de Inicio: 8hs. Hora de fin: 11hs. Duración: 3hs. Crédito restante: 0";
		assertEquals(data, outContent.toString());
	}
	
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	}
	
}
