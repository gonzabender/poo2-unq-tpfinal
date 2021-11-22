package tpfinal;

public class MovementSensorDesactivado extends EstadoMoveS {
	//Estado donde no se mandan notificaciones y no se puede cambiar a modo automatico hasta que
	//se vuelva a activar el sensor de movimiento

	@Override
	protected String walking(AppUsuario appUsuario, EstadoApp estado, SEM sem, Celular celular, String patente,
			int horaActual) {
		return "";
	}

	@Override
	protected String driving(AppUsuario appUsuario, EstadoApp estado, SEM sem, Celular celular) {
		return "";
	}
	
	@Override
	protected void toggleMovementSensor(AppUsuario app) {
		app.setEstadoMoveS(new Driving());
	}

}
