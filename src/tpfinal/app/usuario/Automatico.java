package tpfinal.app.usuario;

import java.time.LocalTime;

import tpfinal.sistema.SEM;
import tpfinal.usuario.AppUsuario;
import tpfinal.usuario.Celular;

public class Automatico extends EstadoApp{
	//Estado donde la app se encarga de iniciar y finalizar estacionamientos, el usuario no puede
	//hacerlo en este estado

/*	String ret="";
	if (this.automatico && !driving){
		this.driving= true;
		this.finalizarEstacionamiento();
		ret= this.alertaFinEAuto();
	}
	else if (this.automatico && driving){
		this.driving=false;
		this.iniciarEstacionamiento();
		ret=this.alertaInicioEAuto();
	}*/
	
	@Override
	public void cambiarModo(AppUsuario app) {
		app.setEstado(new Manual());
	}

	@Override
	public String iniciarEstacionamiento(SEM sem, Celular celular, String patente, LocalTime horaActual) {
		return "No se puede iniciar estacionamiento en modo automatico";
	}
	
	@Override
	public String finalizarEstacionamiento(AppUsuario app) {
		return "No se puede finalizar estacionamiento en modo automatico";
	}


	
	//Finaliza estacionamiento,
	//hay que arreglar el finalizar estacionamiento del sem para que tome solo el celular y descuente el saldo
	@Override
	public String cambieAManejar(SEM sem, Celular celular) {
		return sem.finalizarEstacionamiento(celular);
	}


	
	//Se estaciona
	//hay que revisar un poco el iniciar del sem
	@Override
	public String cambieACaminar(SEM sem, Celular celular, String patente, LocalTime horaActual) {
		return sem.iniciarEstacionamiento(celular,patente,horaActual);
	}


	
	
}
