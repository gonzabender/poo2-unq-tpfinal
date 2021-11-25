package tpfinal;

public class Caminando extends EstadoMoveS {

	@Override
	protected void caminando (AppUsuario appUsuario) {
		//no hace nada
	}

	@Override
	protected void manejando(AppUsuario appUsuario) {
		appUsuario.setEstadoMoveS(new Manejando());
		appUsuario.ahoraEstasManejando();
	}

	@Override
	protected void toggleMovementSensor(AppUsuario appUsuario) {
		appUsuario.setEstadoMoveS(new MovementSensorDesactivado());
	}

}
