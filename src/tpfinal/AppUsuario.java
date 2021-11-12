package tpfinal;

public class AppUsuario  {
/*En un principio se penso hacer la clase AppUsuario con 2 subclases
 *una que se encargue del funcionamiento manual y otra que se encargue
 *del funcionamiento automatico. Esto resulto en un problema, que es que
 *al cambiar de modo, el usuario es el que se encarga de decir qué aplicacion
 *va a estar en modo automatico, puesto que el cambio de modo se implementaba
 *devolviendo una nueva AppUsuario, ya sea manual o automatica.
 *Ej: 	AppUsuario miAppEst= new AppUsuario(sem, patente, celular);
 *		AppUsuario miAppEst= miAppEst.cambiarModo();
 *
 *Edit: Resuelto con state ^
 *
 **/
	private SEM sem;
	private String patente;
	private Celular celular;
	private int horaActual;
	private EstadoApp estado; //false indica que la app no esta en automatico
								//true indica que la app esta en automatico
	
	private boolean driving;	//false indica que la persona esta caminando
								//true indica que la persona esta al volante
	
	private ZonaSem posicion;
	
	
	public AppUsuario(SEM sem, String patente, Celular celular) {
		this.sem=sem;
		this.celular=celular;
		this.patente=patente;
		this.estado=new Manual();
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
	public int getHoraActual() {
		return horaActual;
	}
	
	public void setHoraActual(int hora) {
		this.horaActual = hora;
	}
	
	public void setPosicion(ZonaSem zona) {
		this.posicion= zona;
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
	public void iniciarEstacionamiento (){
		this.estado.iniciarEstacionamiento(this);	//Delegado a EstadoApp
	}

	//devuelve la hora de iicio de estacionamiento, la hora de fin, la cantidad de horas estacionado
	//y el costo del estacionamiento (en lugar de un string se podria usar una nueva clase)
	public void finalizarEstacionamiento() {
		this.estado.finalizarEstacionamiento(this);	//Delegado a EstadoApp
	}	
	
	public void cambiarModo() {
		this.estado.cambiarModo(this);	//Delegado a EstadoApp
	}

	public boolean estaEstacionado() {
		if (this.posicion==null) {
			return false;
		}else {
			return this.posicion.estaVigente(this.patente);
		}
	}


/////////////////////////Simil-Observer/////////////////////////////////////////////////
	
	public void driving() {
		if(!this.driving) {
			this.driving=true;
			this.estado.notificar(this.driving, this);//notifica que esta manejando si estaba caminando antes
		}	
	}
	
	public void walking() {
		if(this.driving) {
			this.driving=false;
			this.estado.notificar(this.driving, this);//nofifica que no esta manejando si estaba manejando antes.
		}	
	}

//////////////////////////////////////////////////////////////////////////////////

	public void toggleMovementSensor() {
		this.estado.toggleMovementSensor(this);	//Delegado a EstadoApp
	}
	
	void setEstado(EstadoApp estado) {
		this.estado=estado;
	};
}