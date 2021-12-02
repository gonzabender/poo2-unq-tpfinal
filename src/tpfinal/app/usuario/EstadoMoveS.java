package tpfinal.app.usuario;

public enum EstadoMoveS {

	Manejando {

		@Override
		public void caminando(AppUsuario appUsuario) {
			appUsuario.setEstadoMoveS(EstadoMoveS.Caminando);
			appUsuario.ahoraEstasCaminando();
		}	

		@Override
		public void manejando(AppUsuario appUsuario) {
			//no hace nada
		}
		
	}, Caminando {

		@Override
		public void caminando (AppUsuario appUsuario) {
			//no hace nada
		}

		@Override
		public void manejando(AppUsuario appUsuario) {
			appUsuario.setEstadoMoveS(EstadoMoveS.Manejando);
			appUsuario.ahoraEstasManejando();
		}

		
	}, MoveSDesactivado{
		
		@Override
		public void caminando(AppUsuario appUsuario) {
			//no hace nada
		}

		@Override
		public void manejando(AppUsuario appUsuario) {
			//no hace nada
		}
		
	};
	/**
	 * Si anteriormente estaba manejando no hace nada, si estaba caminando cambia el estado a manejando	 
	 * @param appUsuario Le indica si maneja o camina
	 */
	public abstract void manejando(AppUsuario appUsuario);
	/**
	 * Si anteriormente estaba caminando no hace nada, si estaba manejando cambia el estado a caminando	 
	 * @param appUsuario Le indica si maneja o camina
	 */
	public abstract void caminando(AppUsuario appUsuario);

}
