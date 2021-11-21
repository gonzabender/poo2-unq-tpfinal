package tpfinal;

public class MovementSensorDesactivado extends EstadoMoveS {
	//Estado donde no se mandan notificaciones y no se puede cambiar a modo automatico hasta que
	//se vuelva a activar el sensor de movimiento

	@Override
	protected void walking(AppUsuario appUsuario, EstadoApp estado, SEM sem, Celular celular, String patente,
			int horaActual) {
	}

	@Override
	protected void driving(AppUsuario appUsuario, EstadoApp estado, SEM sem, Celular celular) {
	}
	
	@Override
	protected void toggleMovementSensor(AppUsuario app) {
		app.setEstadoMoveS(new Driving());
	}

}
