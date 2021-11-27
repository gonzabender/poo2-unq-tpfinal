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
			String alerta="";
			if (sem.tieneSaldoSuficiente(celular)){
				app.setEstadoEstacionamiento(EstadoEstacionamiento.EstaEstacionado);
				alerta= sem.iniciarEstacionamiento(celular, patente, horaActual);
			} else {
				alerta="No tiene credito suficiente para iniciar estacionamiento";
			}
			return alerta;
		}
		
	}, EstaEstacionado {
		@Override
		public String iniciarEstacionamiento(AppUsuario app, SEM sem, Celular celular, String patente, LocalTime horaActual) {
			return "No se puede iniciar estacionamiento porque ya esta estacionado";
		}
	};

	public abstract String iniciarEstacionamiento(AppUsuario app, SEM sem, Celular celular, String patente, LocalTime horaActual);
	
}
