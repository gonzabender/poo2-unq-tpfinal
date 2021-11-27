package tpfinal.app.usuario;

import java.time.LocalTime;

import tpfinal.sistema.SEM;

public class Automatico extends EstadoApp{

	//Finaliza estacionamiento,
	//hay que arreglar el finalizar estacionamiento del sem para que tome solo el celular y descuente el saldo
	//problema con alerta
	@Override
	public void cambieAManejar(AppUsuario appUsuario) {
		appUsuario.finalizarEstacionamiento();
	}


	
	//Se estaciona
	//hay que revisar un poco el iniciar del sem
	//problema con alerta
	@Override
	public void cambieACaminar(AppUsuario appUsuario) {
		appUsuario.iniciarEstacionamiento();;
	}


	
	
}
