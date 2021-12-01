package tpfinal.app.usuario;

public class MovementSensorDesactivado extends EstadoMoveS {
	//Estado donde no se mandan notificaciones y no se puede cambiar a modo automatico hasta que
	//se vuelva a activar el sensor de movimiento

	@Override
	public void caminando(AppUsuario appUsuario) {
		//no hace nada
	}

	@Override
	public void manejando(AppUsuario appUsuario) {
		//no hace nada
	}
}
