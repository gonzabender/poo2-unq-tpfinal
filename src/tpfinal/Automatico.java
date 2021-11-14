package tpfinal;

public class Automatico extends EstadoApp{
	//Estado donde la app se encarga de iniciar y finalizar estacionamientos, el usuario no puede
	//hacerlo en este estado

/*	String ret="";
	if (this.automatico && !driving){
		this.driving= true;
		this.finalizarEstacionamiento();
		ret= this.alertaFinEAuto();
	}
	else if (this.automatico && driving){
		this.driving=false;
		this.iniciarEstacionamiento();
		ret=this.alertaInicioEAuto();
	}*/
	
	@Override
	protected void cambiarModo(AppUsuario app) {
		app.setEstado(new Manual());
	}

	@Override
	protected void notificar(boolean driving, AppUsuario app) {
		if (!driving){
			app.getCelular().alerta("Se ha estacionado. "
									+ app.getSem().iniciarEstacionamiento(app.getCelular(),app.getPatente(),app.getHoraActual()));
		}
		else if (driving){
			app.getCelular().alerta("Se ha finalizado el estacionamiento. " 
									+ app.getSem().finalizarEstacionamiento(app.getCelular().getNúmero()));
			
		}
	}

	@Override
	protected void toggleMovementSensor(AppUsuario app) {
		app.setEstado(new MovementSensorDisabled());
		app.getCelular().alerta("Se ha desactivado el modo automatico porque se desactivo el sensor de movimiento");
	}

	@Override
	protected void iniciarEstacionamiento(AppUsuario app) {
		app.getCelular().alerta("No se puede iniciar estacionamiento en modo automatico");
	}

	@Override
	protected void finalizarEstacionamiento(AppUsuario app) {
		app.getCelular().alerta("No se puede finalizar estacionamiento en modo automatico");
	}
	
	
}
