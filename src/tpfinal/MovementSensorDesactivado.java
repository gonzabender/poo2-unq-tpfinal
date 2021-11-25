package tpfinal;

public class MovementSensorDesactivado extends EstadoMoveS {
	//Estado donde no se mandan notificaciones y no se puede cambiar a modo automatico hasta que
	//se vuelva a activar el sensor de movimiento

	@Override
	protected void caminando(AppUsuario appUsuario) {
		//no hace nada
	}

	@Override
	protected void manejando(AppUsuario appUsuario) {
		//no hace nada
	}
	
	@Override
	protected void toggleMovementSensor(AppUsuario app) {
		app.setEstadoMoveS(new Manejando());
	}

}
