package tpfinal.app.usuario;

import tpfinal.usuario.AppUsuario;

public class Caminando extends EstadoMoveS {

	@Override
	public void caminando (AppUsuario appUsuario) {
		//no hace nada
	}

	@Override
	public void manejando(AppUsuario appUsuario) {
		appUsuario.setEstadoMoveS(new Manejando());
		appUsuario.ahoraEstasManejando();
	}

	@Override
	public void toggleMovementSensor(AppUsuario appUsuario) {
		appUsuario.setEstadoMoveS(new MovementSensorDesactivado());
	}

}
