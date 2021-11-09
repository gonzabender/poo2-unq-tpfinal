package tpfinal;

import java.util.Observer;

public class AppUsuario implements MovementSensor {
/*En un principio se penso hacer la clase AppUsuario con 2 subclases
 *una que se encargue del funcionamiento manual y otra que se encargue
 *del funcionamiento automatico. Esto resulto en un problema, que es que
 *al cambiar de modo, el usuario es el que se encarga de decir qué aplicacion
 *va a estar en modo automatico, puesto que el cambio de modo se implementaba
 *devolviendo una nueva AppUsuario, ya sea manual o automatica.
 *Ej: 	AppUsuario miAppEst= new AppUsuario(sem, patente, celular);
 *		AppUsuario miAppEst= miAppEst.cambiarModo();
 **/
	private SEM sem;
	private String patente;
	private Celular celular;
	private int horaActual;
	private boolean automatico; //false indica que la app no esta en automatico
								//true indica que la app esta en automatico
	
	private boolean driving;	//false indica que la persona esta caminando
								//true indica que la persona esta al volante
	
	public AppUsuario(SEM sem, String patente, Celular celular) {
		this.sem=sem;
		this.celular=celular;
		this.patente=patente;
		this.automatico=false;
		this.driving=true;
	}

	public SEM getSem() {
		return sem;
	}

	public String getPatente() {
		return patente;
	}

	public Celular getCelular() {
		return celular;
	}

	private void setSem(SEM sem) {
		this.sem=sem;
	}
	
	public void cargarCredito(PuntoDeVenta pv, int monto) {
		pv.cargarCelular(this.celular, monto);
	}

	//devuelve el saldo disponible
	public int consultaSaldo() {
		return this.sem.consultarSaldo(this.celular);
	}

	//devuelve la hora de inicio de estacionamiento, la cantidad maxima de horas que es posible
	//estacionar, y si no tiene suficiente saldo para estacionar devuelve el texto
	//"Saldo insuficiente, Estacionamiento no permitido" (tambien podria hacerse con una clase y una excepcion)
	public String iniciarEstacionamiento (){
		return this.sem.iniciarEstacionamiento(this.celular,this.patente, this.horaActual);
	}

	//devuelve la hora de iicio de estacionamiento, la hora de fin, la cantidad de horas estacionado
	//y el costo del estacionamiento (en lugar de un string se podria usar una nueva clase)
	public String finalizarEstacionamiento() {
		return this.sem.finalizarEstacionamiento(this.celular.getNúmero());
	}	
	
	public void cambiarModo() {
		this.automatico= !this.automatico;
	}


//////////////////////////////////////////////////////////////////////////////////
/////////////////////////Observer/////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////
	//Podria separarse con un notify
	public void driving() {
		if (this.automatico && !this.driving){
			this.driving= true;
			this.finalizarEstacionamiento();
			this.alertaFinEAuto();
		}else if (!this.automatico && this.sem.estacionado(patente)) {
			this.alertaFinE();
		}
	}
	
	public void walking() {
		if (this.automatico && this.driving){
			this.driving=false;
			this.iniciarEstacionamiento();
			this.alertaInicioEAuto();
		}else if (!this.automatico && !this.sem.estacionado(patente)){
			this.alertaInicioE();
		}
	}
//////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////	
	
	//Observer
	public void alertaInicioE() {
		//DO SOMETHING
	};
	//Observer
	public void alertaFinE() {
		//DO SOMETHING
	};
	//Observer
	public void alertaInicioEAuto() {
		//DO SOMETHING
	};
		//Observer
	public void alertaFinEAuto() {
		//DO SOMETHING
	};
}