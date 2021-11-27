package tpfinal.app.usuario;

import java.time.LocalTime;

import tpfinal.sistema.SEM;

public enum EstadoEstacionamiento {
	
	NoEstaEnZona {
		@Override
		public String iniciarEstacionamiento(AppUsuario app, SEM sem, Celular celular, String patente, LocalTime horaActual) {
			return "No puede iniciar estacionamiento porque no se encuentra en una zona de estacionamiento medido";
		}
		
	}, NoEstaEstacionado {
		@Override
		public String iniciarEstacionamiento(AppUsuario app, SEM sem, Celular celular, String patente, LocalTime horaActual) {
			app.setEstadoEstacionamiento(EstadoEstacionamiento.EstaEstacionado);
			return sem.iniciarEstacionamiento(celular, patente, horaActual);
		}
		
	}, EstaEstacionado {
		@Override
		public String iniciarEstacionamiento(AppUsuario app, SEM sem, Celular celular, String patente, LocalTime horaActual) {
			return "No se puede iniciar estacionamiento porque ya esta estacionado";
		}
	};

	public abstract String iniciarEstacionamiento(AppUsuario app, SEM sem, Celular celular, String patente, LocalTime horaActual);
	
}
