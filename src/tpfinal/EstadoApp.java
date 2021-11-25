package tpfinal;

import java.time.LocalTime;

public abstract class EstadoApp {

	protected abstract void cambiarModo(AppUsuario appUsuario);

	protected abstract String iniciarEstacionamiento(SEM sem, Celular celular, String patente, LocalTime horaActual);
	
	protected abstract String finalizarEstacionamiento(AppUsuario appUsuario);
	
	protected abstract String cambieAManejar(SEM sem, Celular celular);

	protected abstract String cambieACaminar(SEM sem, Celular celular, String patente, LocalTime horaActual);

}
