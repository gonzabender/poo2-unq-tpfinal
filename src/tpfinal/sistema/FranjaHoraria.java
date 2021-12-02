package tpfinal.sistema;

import java.time.LocalTime;

import tpfinal.app.usuario.Celular;

public enum FranjaHoraria {
	Inactiva {
		@Override
		protected String iniciarEstacionamiento(SEM sem, Celular celular, String patente, LocalTime hora) {
			return "No es requerido estacionar entre las 20:00hs y las 07:00hs";
		}

		@Override
		protected void finalizarTodosLosEstacionamientos(SEM sem) {
			sem.finalizarTodosLosEstacionamientosPermitido();
		}
	}, Activa {
		@Override
		protected String iniciarEstacionamiento(SEM sem, Celular celular, String patente, LocalTime hora) {
			return sem.estacionamientoPermitido(celular,patente,hora);
		}

		
	};

	protected abstract String iniciarEstacionamiento(SEM sem, Celular celular, String patente, LocalTime hora);

	protected  void finalizarTodosLosEstacionamientos(SEM sem) {
		// Hook para Inactiva.
	}
	
}
