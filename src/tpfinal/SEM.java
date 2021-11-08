package tpfinal;

import java.util.List;
import java.util.ArrayList;

public class SEM {

	private List<ZonaDeSem> zonasDeEstacionamiento = new ArrayList<ZonaDeSem>();
	private List<Estacionamiento> estacionamientosEnCurso = new ArrayList<Estacionamiento>();
	private List<Compra> compras = new ArrayList<Compra>();
	private List<Infracción> infracciones = new ArrayList<Infracción>();

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

	public void addInfracción() {
		// Este espacio depende de la creación del sector de infraciones.
	}
	
	public void addCompra(Compra compra) {
		this.compras.add(compra);
	}

}
