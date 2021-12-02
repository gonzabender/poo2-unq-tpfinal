package tpfinal.app.usuario;

public enum Modo {
	
	Automatico{
		
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


	}, Manual{

		@Override
		public String alertaIniciar(AppUsuario appUsuario) {
			return "Aun no se ha estacionado";
		}


		@Override
		public String alertaFinalizar(AppUsuario appUsuario) {
			return "Aun no se ha finalizado el estacionamiento";
		}

	};
	
	
	public abstract String alertaIniciar(AppUsuario appUsuario);

	public abstract String alertaFinalizar(AppUsuario appUsuario);

}
