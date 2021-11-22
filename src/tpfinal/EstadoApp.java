package tpfinal;

public abstract class EstadoApp {

	protected abstract void cambiarModo(AppUsuario appUsuario);

	protected abstract void iniciarEstacionamiento(AppUsuario appUsuario);

	protected abstract void finalizarEstacionamiento(AppUsuario appUsuario);
	
	protected abstract String driving(SEM sem, Celular celular);

	protected abstract String walking(SEM sem, Celular celular, String patente, int horaActual);
}
