package tpfinal;

public abstract class EstadoApp {

	protected abstract void cambiarModo(AppUsuario appUsuario);

	protected abstract String iniciarEstacionamiento(AppUsuario appUsuario);

	protected abstract String finalizarEstacionamiento(AppUsuario appUsuario);
	
	protected abstract String driving(SEM sem, Celular celular);

	protected abstract String walking(SEM sem, Celular celular, String patente, int horaActual);
}
