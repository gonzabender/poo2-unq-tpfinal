package tpfinal;

public abstract class EstadoApp {

	protected abstract void cambiarModo(AppUsuario appUsuario);

	protected abstract void notificar(boolean driving, AppUsuario appUsuario);

	protected abstract void toggleMovementSensor(AppUsuario appUsuario);

	protected abstract void iniciarEstacionamiento(AppUsuario appUsuario);

	protected abstract void finalizarEstacionamiento(AppUsuario appUsuario);
	
	
}
