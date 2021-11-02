package tpfinal;

public abstract class AppUsuario implements MovementSensor {

	private SEM sem;
	private String patente;
	private int celular;
	private boolean automatico; //false indica que la app no esta en automatico
								//true indica que la app esta en automatico
	
	public AppUsuario(SEM sem, String patente, int celular) {
		this.sem=sem;
		this.celular=celular;
		this.patente=patente;
		this.automatico=false;
	}

	public SEM getSem() {
		return sem;
	}

	public String getPatente() {
		return patente;
	}

	public int getCelular() {
		return celular;
	}

	private void setSem(SEM sem) {
		this.sem=sem;
	}
	
	public void cargarCredito(PuntoDeVenta pv) {
		pv.cargarCredito(this.celular);
	}

	//devuelve el saldo disponible
	public int consultaSaldo() {
		return this.sem.consultarSaldo(this.celular);
	}

	//devuelve la hora de inicio de estacionamiento, la cantidad maxima de horas que es posible
	//estacionar, y si no tiene suficiente saldo para estacionar devuelve el texto
	//"Saldo insuficiente, Estacionamiento no permitido" (tambien podria hacerse con una clase y una excepcion)
	public String iniciarEstacionamiento (){
		return this.sem.iniciarEstacionamiento(this.celular,this.patente);
	}

	//devuelve la hora de iicio de estacionamiento, la hora de fin, la cantidad de horas estacionado
	//y el costo del estacionamiento (en lugar de un string se podria usar una nueva clase)
	public String finalizarEstacionamiento() {
		return this.sem.finalizarEstacionamiento(this.celular);
	}	
	
	public void cambiarModo() {
		this.automatico= !this.automatico;
		this.modoAutomatico();
	}
	
	//A implementar con observer (puede que con un while, 
	//siendo la condicion "automatico")
	public void modoAutomatico() {
		if (this.automatico){
			//DO SOMETHING	
		}else {
			//STOP DOING IT
		}
	}
	
	//Observer
	public void alertaInicioE() {
		//DO SOMETHING
	};
	//Observer
	public void alertaFinE() {
		//DO SOMETHING
	};
	
}