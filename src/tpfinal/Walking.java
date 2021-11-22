package tpfinal;

public class Walking extends EstadoMoveS {

	@Override
	protected String walking(AppUsuario appUsuario, EstadoApp estado, SEM sem, Celular celular, String patente,
			int horaActual) {
		return "";
	}

	@Override
	protected String driving(AppUsuario appUsuario, EstadoApp estado, SEM sem, Celular celular) {
		appUsuario.setEstadoMoveS(new Driving());
		return estado.driving(sem,celular);
	}

	@Override
	protected void toggleMovementSensor(AppUsuario appUsuario) {
		appUsuario.setEstadoMoveS(new MovementSensorDesactivado());
	}

}
