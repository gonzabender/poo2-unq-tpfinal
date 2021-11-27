package tpfinal.app.usuario;

public abstract class EstadoMoveS {

	public abstract void toggleMovementSensor(AppUsuario appUsuario);
	/**
	 * Si anteriormente estaba manejando no hace nada, si estaba caminando cambia el estado a manejando	 
	 * @param appUsuario Le indica si maneja o camina
	 */
	public abstract void manejando(AppUsuario appUsuario);
	/**
	 * Si anteriormente estaba caminando no hace nada, si estaba manejando cambia el estado a caminando	 
	 * @param appUsuario Le indica si maneja o camina
	 */
	public abstract void caminando(AppUsuario appUsuario);

}
