package tpfinal.app.usuario;

/**
 * Cada una determinada cantidad de segundos las apps que implementen esta
 * interfaz recibir�n uno de estos dos mensajes por parte del tel�fono celular
 * en que se encuentran instaladas. Un mismo mensaje puede (y suele) ser
 * recibido de forma repetida.
 *
 */
public interface MovementSensor {
	// Indica que el desplazamiento se realiza a bordo de un veh�culo.
	public void driving();

	// Indica que el desplazamiento se realiza caminando.
	public void walking();
}