package tpfinal.app.usuario;

import java.time.LocalTime;

import tpfinal.sistema.SEM;

public class Manual extends EstadoApp {
	//Estado donde el usuario se encarga de iniciar y finalizar estacionamientos
	
	
	@Override
	public void cambiarModo(AppUsuario app) {
		app.setEstado(new Automatico());
	}

	@Override
	public String iniciarEstacionamiento(SEM sem, Celular celular, String patente, LocalTime horaActual) {
	
		return sem.iniciarEstacionamiento(celular,patente,horaActual);
	}

	@Override
	public String finalizarEstacionamiento(AppUsuario app) {
		
		return app.getSem().finalizarEstacionamiento(app.getCelular());
		
	}
	
	
	//hay que ver una manera de que sepa si esta estacionado
	//aun no alerta nada
	@Override
	public String cambieAManejar(SEM sem, Celular celular) {
	/*	boolean estacionado= app.estaEstacionado();
	  	if(estacionado && driving){
			//alerta si esta no esta en modo automatico y comenzo a manejar
			app.getCelular().alerta("Aun no ha finalizado el estacionamiento");
		}*/
		return "Aun no ha finalizado el estacionamiento";
	}

	
	//hay que ver una manera de que sepa si esta estacionado
	//aun no alerta nada
	@Override
	public String cambieACaminar(SEM sem, Celular celular, String patente, LocalTime horaActual) {
		/*boolean estacionado= app.estaEstacionado();
		if (!estacionado && !driving){
			//alerta si no esta en automatico y dejo de manejar
			app.getCelular().alerta("Aun no se ha estacionado");
		}*/
		return "Aun no se ha estacionado";
	}

}
