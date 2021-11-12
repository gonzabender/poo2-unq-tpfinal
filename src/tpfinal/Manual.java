package tpfinal;

public class Manual extends EstadoApp {
	//Estado donde el usuario se encarga de iniciar y finalizar estacionamientos
	
	/*else if (!this.automatico && !driving){
		//alerta si no esta en automatico y dejo de manejar
		ret=this.alertaInicioE();
	}
	else if (!this.automatico) {
		//alerta si esta no esta en modo automatico y comenzo a manejar
		ret=this.alertaFinE();
	}*/
	
	@Override
	protected void cambiarModo(AppUsuario app) {
		app.setEstado(new Automatico());
	}

	@Override
	protected void notificar(boolean driving,AppUsuario app) {
		boolean estacionado= app.estaEstacionado();
		if (!estacionado && !driving){
			//alerta si no esta en automatico y dejo de manejar
			app.getCelular().alerta("Aun no se ha estacionado");
		}else if(estacionado && driving){
			//alerta si esta no esta en modo automatico y comenzo a manejar
			app.getCelular().alerta("Aun no ha finalizado el estacionamiento");
		}
	}

	@Override
	protected void toggleMovementSensor(AppUsuario app) {
		app.setEstado(new MovementSensorDisabled());
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
