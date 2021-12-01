package tpfinal.app.usuario;

public class Caminando extends EstadoMoveS {

	@Override
	public void caminando (AppUsuario appUsuario) {
		//no hace nada
	}

	@Override
	public void manejando(AppUsuario appUsuario) {
		appUsuario.setEstadoMoveS(new Manejando());
		appUsuario.ahoraEstasManejando();
	}
}
