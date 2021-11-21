package tpfinal;

public class Walking extends EstadoMoveS {

	@Override
	protected void walking(AppUsuario appUsuario, EstadoApp estado, SEM sem, Celular celular, String patente,
			int horaActual) {
	}

	@Override
	protected void driving(AppUsuario appUsuario, EstadoApp estado, SEM sem, Celular celular) {
		appUsuario.setEstadoMoveS(new Driving());
		estado.driving(sem,celular);
	}

	@Override
	protected void toggleMovementSensor(AppUsuario appUsuario) {
		appUsuario.setEstadoMoveS(new MovementSensorDesactivado());
	}

}
