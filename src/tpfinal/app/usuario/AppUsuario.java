package tpfinal.app.usuario;

import java.time.LocalTime;

import tpfinal.sistema.SEM;

public class AppUsuario  {

	private SEM sem;
	private String patente;
	private Celular celular;
	private LocalTime horaActual;
	private Modo modo; 
	private EstadoMoveS estadoMoveS;
	private EstadoEstacionamiento estadoEstacionamiento;
	
	
	public AppUsuario(SEM sem, String patente, Celular celular) {
		this.sem=sem;
		this.celular=celular;
		this.patente=patente;
		this.modo= Modo.Manual;
		this.estadoMoveS= EstadoMoveS.Manejando;
		this.estadoEstacionamiento= EstadoEstacionamiento.NoEstaEstacionado;
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
	public LocalTime getHoraActual() {
		return horaActual;
	}
	
	public void setHoraActual(LocalTime hora) {
		this.horaActual = hora;
	}
	
	/**
	 * 
	 * @return El saldo del celular
	 */
	public int consultaSaldo() {
		return this.celular.getSaldo();
	}

	/**
	 * Alerta de la hora de inicio de estacionamiento, la cantidad maxima de horas que es posible 
	 * estacionar, y si no tiene suficiente saldo para estacionar devuelve el texto 
	 * "No tiene credito suficiente para iniciar estacionamiento"
	 */
	public void iniciarEstacionamiento (){
		this.celular.alerta( this.estadoEstacionamiento.iniciarEstacionamiento(this,this.sem,this.celular,this.patente,this.horaActual) );	//Delegado a EstadoApp
	}

	
	/**
	 * Alerta de la hora de inicio de estacionamiento, la hora de fin, la cantidad de horas estacionado 
	 * y el costo del estacionamiento 
	 */
	public void finalizarEstacionamiento() {
		this.celular.alerta(this.estadoEstacionamiento.finalizarEstacionamiento(this.sem,this.celular));	//Delegado a EstadoApp
	}	
	
	/**
	 * Mensaje recibido constantemente por la app para determinar si el usuario est? manejando.
	 * 
	 * @see #walking()
	 */
	public void driving() {
		this.estadoMoveS.manejando(this);
		//delega al EstadoMoveS el cual vuelve a delgar 
	}
	
	
	/**
	 * Mensaje recibido constantemente por la app para determinar si el usuario est? caminando
	 * 
	 * @see #driving()
	 */
	public void walking() {
		this.estadoMoveS.caminando(this);
		//delega al EstadoMoveS el cual vuelve a delgar 
	}
	
	public void setEstadoMoveS(EstadoMoveS estadoMoveS) {
		this.estadoMoveS=estadoMoveS;
	}

	protected void ahoraEstasCaminando() {
		this.estadoEstacionamiento.cambieACaminar(this);
	}

	protected void ahoraEstasManejando() {
		this.estadoEstacionamiento.cambieAManejar(this);
	}

	public EstadoEstacionamiento getEstadoEstacionamiento() {
		return this.estadoEstacionamiento;
	}

	
	/**
	 * Su funcion es probar causisticas de si esta dentro de una zona de estacionamiento medido
	 */
	public void estaDentroDeZona() {
		this.estadoEstacionamiento= EstadoEstacionamiento.NoEstaEstacionado;
	}

	/**
	 * Su funcion es probar causisticas de si esta fuera de una zona de estacionamiento medido
	 */
	public void estaFueraDeZona() {
		this.estadoEstacionamiento= EstadoEstacionamiento.NoEstaEnZona;
	}

	protected void setEstadoEstacionamiento(EstadoEstacionamiento estadoEstacionamiento) {
		this.estadoEstacionamiento=estadoEstacionamiento;
	}

	public void cambiarAManual() {
		this.modo=Modo.Manual;
	}

	public void cambiarAAutomatico() {
		this.modo=Modo.Automatico;
	}

	public void cambieACaminarYNoEstoyEstacionado() {
		this.celular.alerta(this.modo.alertaIniciar(this));
	}

	public void cambieAManejarYEstoyEstacionado() {
		this.celular.alerta(this.modo.alertaFinalizar(this));
	}

	
	/**
	 * Desactiva el sensor de movimiento
	 */
	public void desactivarMoveS() {
		this.estadoMoveS=EstadoMoveS.MoveSDesactivado;
	}
	
	/**
	 * Activa el sensor de movimiento y asume que esta manejando
	 */
	public void activarMoveS() {
		this.estadoMoveS=EstadoMoveS.Manejando;
	}
}