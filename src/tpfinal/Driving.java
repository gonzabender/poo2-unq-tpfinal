package tpfinal;

public class Driving extends EstadoMoveS{

	@Override
	protected String walking(AppUsuario appUsuario,EstadoApp estado, SEM sem, Celular celular, String patente, int horaActual) {
		appUsuario.setEstadoMoveS(new Walking());
		return estado.walking(sem,celular,patente,horaActual);
	}	

	@Override
	protected String driving(AppUsuario appUsuario,EstadoApp estado, SEM sem, Celular celular) {
		return "";
	}

	@Override
	protected void toggleMovementSensor(AppUsuario appUsuario) {
		appUsuario.setEstadoMoveS(new MovementSensorDesactivado());
	}

}
