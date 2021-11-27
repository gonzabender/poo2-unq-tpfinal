package tpfinal.app.usuario;

public class Manejando extends EstadoMoveS{

	@Override
	public void caminando(AppUsuario appUsuario) {
		appUsuario.setEstadoMoveS(new Caminando());
		appUsuario.ahoraEstasCaminando();
	}	

	@Override
	public void manejando(AppUsuario appUsuario) {
		//no hace nada
	}

	@Override
	public void toggleMovementSensor(AppUsuario appUsuario) {
		appUsuario.setEstadoMoveS(new MovementSensorDesactivado());
	}

}
