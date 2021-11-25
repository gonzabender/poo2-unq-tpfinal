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
	private EstadoApp estado; 
	private EstadoMoveS estadoMoveS;
	private ZonaSem posicion;
	
	
	public AppUsuario(SEM sem, String patente, Celular celular) {
		this.sem=sem;
		this.celular=celular;
		this.patente=patente;
		this.estado=new Manual();
		this.estadoMoveS= new Manejando();
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

	/**
	 * 
	 * @return El saldo del celular
	 */
	public int consultaSaldo() {
		return this.sem.consultarSaldo(this.celular);
	}

	/**
	 * Alerta de la hora de inicio de estacionamiento, la cantidad maxima de horas que es posible 
	 * estacionar, y si no tiene suficiente saldo para estacionar devuelve el texto 
	 * Saldo insuficiente, Estacionamiento no permitido" 
	 */
	//(puede simplemente devolver un string o tambien podria hacerse con una clase y una excepcion)
	public void iniciarEstacionamiento (){
		this.celular.alerta( this.estado.iniciarEstacionamiento(this.sem,this.celular,this.patente,this.horaActual) );	//Delegado a EstadoApp
	}

	
	/**
	 * Alerta de la hora de inicio de estacionamiento, la hora de fin, la cantidad de horas estacionado 
	 * y el costo del estacionamiento 
	 */
	//(puede simplemente devolver un string o en lugar de un string se podria usar una nueva clase)
	public void finalizarEstacionamiento() {
		this.celular.alerta( this.estado.finalizarEstacionamiento(this));	//Delegado a EstadoApp
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
	
	
	/**
	 * Mensaje recibido constantemente por la app para determinar si el usuario está manejando.
	 * 
	 * @see #walking()
	 */
	public void driving() {
		this.estadoMoveS.manejando(this);
		//delega al EstadoMoveS el cual vuelve a delgar 
	}
	
	
	/**
	 * Mensaje recibido constantemente por la app para determinar si el usuario está caminando
	 * 
	 * @see #driving()
	 */
	public void walking() {
		this.estadoMoveS.caminando(this);
		//delega al EstadoMoveS el cual vuelve a delgar 
	}

	/**
	 * Desactiva el MovementSensor si esta activado o lo activa si esta desactivado
	 */
	public void toggleMovementSensor() {
		this.estadoMoveS.toggleMovementSensor(this);	//Delegado a EstadoMoveS
	}
	
	void setEstado(EstadoApp estado) {
		this.estado=estado;
	}

	protected void setEstadoMoveS(EstadoMoveS estadoMoveS) {
		this.estadoMoveS=estadoMoveS;
	}

	public void ahoraEstasCaminando() {
		this.celular.alerta(this.estado.cambieACaminar(sem, celular, patente, horaActual));
	}

	public void ahoraEstasManejando() {
		this.celular.alerta(this.estado.cambieAManejar(sem, celular));
	};
}