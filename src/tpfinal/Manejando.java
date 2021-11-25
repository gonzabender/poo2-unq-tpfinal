package tpfinal;

public class Manejando extends EstadoMoveS{

	@Override
	protected void caminando(AppUsuario appUsuario) {
		appUsuario.setEstadoMoveS(new Caminando());
		appUsuario.ahoraEstasCaminando();
	}	

	@Override
	protected void manejando(AppUsuario appUsuario) {
		//no hace nada
	}

	@Override
	protected void toggleMovementSensor(AppUsuario appUsuario) {
		appUsuario.setEstadoMoveS(new MovementSensorDesactivado());
	}

}
