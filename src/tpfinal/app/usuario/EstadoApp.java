package tpfinal.app.usuario;

import java.time.LocalTime;

import tpfinal.sistema.SEM;

public abstract class EstadoApp {

	public abstract void cambiarModo(AppUsuario appUsuario);

	public abstract String iniciarEstacionamiento(SEM sem, Celular celular, String patente, LocalTime horaActual);
	
	public abstract String finalizarEstacionamiento(AppUsuario appUsuario);
	
	public abstract String cambieAManejar(SEM sem, Celular celular);

	public abstract String cambieACaminar(SEM sem, Celular celular, String patente, LocalTime horaActual);

}
