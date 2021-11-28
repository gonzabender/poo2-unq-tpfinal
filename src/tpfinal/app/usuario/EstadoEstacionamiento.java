package tpfinal.app.usuario;

import java.time.LocalTime;

import tpfinal.sistema.SEM;

public enum EstadoEstacionamiento {
	
	NoEstaEnZona {
		@Override
		public String iniciarEstacionamiento(AppUsuario app, SEM sem, Celular celular, String patente, LocalTime horaActual) {
			return "No puede iniciar estacionamiento porque no se encuentra en una zona de estacionamiento medido";
		}

		@Override
		protected String finalizarEstacionamiento(SEM sem, Celular celular) {
			// TODO Auto-generated method stub
			return "No se puede finalizar estacionamiento ya que no inicio uno";
		}

		@Override
		protected void cambieACaminar(AppUsuario appUsuario) {
			// no hace nada
		}

		@Override
		protected void cambieAManejar(AppUsuario appUsuario) {
			// no hace nada
		}
		
	}, NoEstaEstacionado {
		@Override
		public String iniciarEstacionamiento(AppUsuario app, SEM sem, Celular celular, String patente, LocalTime horaActual) {
			String alerta="";
			if (sem.tieneSaldoSuficiente(celular)){
				app.setEstadoEstacionamiento(EstadoEstacionamiento.EstaEstacionado);
				alerta= sem.iniciarEstacionamiento(celular, patente, horaActual);
			} else {
				alerta="No tiene credito suficiente para iniciar estacionamiento";
			}
			return alerta;
		}

		@Override
		protected String finalizarEstacionamiento(SEM sem, Celular celular) {
			return "No se puede finalizar estacionamiento ya que no inicio uno";
		}

		/*
		 * 
		 */
		@Override
		protected void cambieACaminar(AppUsuario appUsuario) {
			appUsuario.cambieACaminarYNoEstoyEstacionado();
		}

		@Override
		protected void cambieAManejar(AppUsuario appUsuario) {
			//no hace nada
		}
		
	}, EstaEstacionado {
		@Override
		public String iniciarEstacionamiento(AppUsuario app, SEM sem, Celular celular, String patente, LocalTime horaActual) {
			return "No se puede iniciar estacionamiento porque ya esta estacionado";
		}

		@Override
		protected String finalizarEstacionamiento(SEM sem, Celular celular) {
			return sem.finalizarEstacionamiento(celular);
		}

		@Override
		protected void cambieACaminar(AppUsuario appUsuario) {
			//no hace nada
		}

		@Override
		protected void cambieAManejar(AppUsuario appUsuario) {
			appUsuario.cambieAManejarYEstoyEstacionado();
		}
	};

	public abstract String iniciarEstacionamiento(AppUsuario app, SEM sem, Celular celular, String patente, LocalTime horaActual);

	protected abstract String finalizarEstacionamiento(SEM sem, Celular celular);

	protected abstract void cambieACaminar(AppUsuario appUsuario);

	protected abstract void cambieAManejar(AppUsuario appUsuario);
	
}
