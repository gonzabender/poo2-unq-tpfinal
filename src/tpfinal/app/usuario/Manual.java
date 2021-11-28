package tpfinal.app.usuario;

public class Manual extends EstadoApp {

	@Override
	public String alertaIniciar(AppUsuario appUsuario) {
		return "Aun no se ha estacionado";
	}


	@Override
	public String alertaFinalizar(AppUsuario appUsuario) {
		return "Aun no se ha finalizado el estacionamiento";
	}

}
