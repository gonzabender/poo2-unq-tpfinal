package tpfinal;

public class MovementSensorDisabled extends EstadoApp {
	//Estado donde no se mandan notificaciones y no se puede cambiar a modo automatico hasta que
	//se vuelva a activar el sensor de movimiento
	

	@Override
	protected void cambiarModo(AppUsuario app) {
		app.getCelular()
		.alerta("El sensor de movimiento esta desactivado, por lo cual no se puede cambiar a modo automatico");
	}

	@Override
	protected void notificar(boolean driving, AppUsuario app) {
	}

	@Override
	protected void toggleMovementSensor(AppUsuario app) {
		app.setEstado(new Manual());
	}

	@Override
	protected void iniciarEstacionamiento(AppUsuario app) {
		app.getCelular().alerta(
			app.getSem().iniciarEstacionamiento(app.getCelular(),
											app.getPatente(),
											app.getHoraActual())
		);
	}

	@Override
	protected void finalizarEstacionamiento(AppUsuario app) {
		app.getCelular().alerta(
				app.getSem().finalizarEstacionamiento(app.getCelular().getNúmero())
		);
	}	
	
}
