package tpfinal;

public abstract class EstadoMoveS {

	/**
	 * Si anteriormente estaba caminando no hace nada, si estaba manejando cambia el estado a caminando
	 * @param appUsuario 	De tipo AppUsuario, para actualizar estado
	 * @param estado		De tipo EstadoApp, se utiliza para delegar comportamiento
	 * @param sem			De tipo SEM
	 * @param celular      	De tipo Celular
	 * @param patente 		De tipo String
	 * @param horaActual	De tipo int
	 */
	protected abstract void walking(AppUsuario appUsuario, EstadoApp estado, SEM sem, Celular celular, String patente, int horaActual);

	/**
	 * Si anteriormente estaba manejando no hace nada, si estaba caminando cambia el estado a manejando
	 * @param appUsuario	De tipo AppUsuario, para actucalizar estado
	 * @param estado		De tipo EstadoApp, se utiliza para delegar comportamiento
	 * @param sem			De tipo SEM
	 * @param celular      	De tipo Celular
	 */
	protected abstract void driving(AppUsuario appUsuario, EstadoApp estado, SEM sem, Celular celular);

	protected abstract void toggleMovementSensor(AppUsuario appUsuario);

}
