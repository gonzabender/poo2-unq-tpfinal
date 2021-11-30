package tpfinal.sistema;

import java.util.List;

public class Entidad {

	/**
	 * Esta clase fue creada para representar una entidad cualquiera. De ser creada
	 * otra, el adapter debe ser modificado o debe ser creado uno nuevo
	 */

	private List<String> informes;

	/**
	 * 
	 * @param arg Recibirá la información de cada movimiento que ocurre en el sistema
	 */
	public void notificar(String arg) {
		this.informes.add(arg);

	}

}
