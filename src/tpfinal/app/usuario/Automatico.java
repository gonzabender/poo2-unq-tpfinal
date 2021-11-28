package tpfinal.app.usuario;

public class Automatico extends EstadoApp{

	@Override
	public String alertaIniciar(AppUsuario appUsuario) {
		appUsuario.iniciarEstacionamiento();
		return "Se ha iniciado estacionamiento";
	}



	@Override
	public String alertaFinalizar(AppUsuario appUsuario) {
		appUsuario.finalizarEstacionamiento();
		return "Se ha finalizado estacionamiento";
	}


	
	
}
