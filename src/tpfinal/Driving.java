package tpfinal;

public class Driving extends EstadoMoveS{

	@Override
	protected void walking(AppUsuario appUsuario,EstadoApp estado, SEM sem, Celular celular, String patente, int horaActual) {
		appUsuario.setEstadoMoveS(new Walking());
		estado.walking(sem,celular,patente,horaActual);
	}	


	@Override
	protected void driving(AppUsuario appUsuario,EstadoApp estado, SEM sem, Celular celular) {
	}

	@Override
	protected void toggleMovementSensor(AppUsuario appUsuario) {
		appUsuario.setEstadoMoveS(new MovementSensorDesactivado());
	}

}
