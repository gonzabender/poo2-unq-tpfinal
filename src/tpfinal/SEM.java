package tpfinal;

import java.util.List;
import java.util.ArrayList;

public class SEM {

	private List<ZonaDeSem> zonasDeEstacionamiento = new ArrayList<ZonaDeSem>();
	private List<Estacionamiento> estacionamientosEnCurso = new ArrayList<Estacionamiento>();
	private List<Compra> compras = new ArrayList<Compra>();
	private List<Infracci�n> infracciones = new ArrayList<Infracci�n>();

	/**
	 * 
	 * @param zona La zona a agregar.
	 */
	public void addZona(ZonaDeSem zona) {
		zonasDeEstacionamiento.add(zona);
	}

	/**
	 * 
	 * @param estacionamiento El estacionamiento a agregar.
	 */
	public void addEstacionamiento(Estacionamiento estacionamiento) {
		estacionamientosEnCurso.add(estacionamiento);
	}

	public void addInfracci�n() {
		// Este espacio depende de la creaci�n del sector de infraciones.
	}
	
	public void addCompra(Compra compra) {
		this.compras.add(compra);
	}

}
