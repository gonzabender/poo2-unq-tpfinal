package tpfinal;

public class Manual extends EstadoApp {
	//Estado donde el usuario se encarga de iniciar y finalizar estacionamientos
	
	
	@Override
	protected void cambiarModo(AppUsuario app) {
		app.setEstado(new Automatico());
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
	
	
	//hay que ver una manera de que sepa si esta estacionado
	//aun no alerta nada
	@Override
	protected void driving(SEM sem, Celular celular) {
	/*	boolean estacionado= app.estaEstacionado();
	  	if(estacionado && driving){
			//alerta si esta no esta en modo automatico y comenzo a manejar
			app.getCelular().alerta("Aun no ha finalizado el estacionamiento");
		}*/
	}

	
	//hay que ver una manera de que sepa si esta estacionado
	//aun no alerta nada
	@Override
	protected void walking(SEM sem, Celular celular, String patente, int horaActual) {
		/*boolean estacionado= app.estaEstacionado();
		if (!estacionado && !driving){
			//alerta si no esta en automatico y dejo de manejar
			app.getCelular().alerta("Aun no se ha estacionado");
		}*/
	}

}
