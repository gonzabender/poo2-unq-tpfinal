package tpfinal;

public class AppUsuarioManual extends AppUsuario{

	public AppUsuarioManual(SEM sem, String patente, int celular) {
		super(sem,patente,celular);
	}

	//devuelve la hora de inicio de estacionamiento, la cantidad maxima de horas que es posible
	//estacionar, y si no tiene suficiente saldo para estacionar devuelve el texto
	//"Saldo insuficiente, Estacionamiento no permitido" (tambien podria hacerse con una clase y una excepcion)
	public String iniciarEstacionamiento (){
		return this.sem.iniciarEstacionamiento(this.getCelular(),this.getPatente());
	}

	//devuelve la hora de iicio de estacionamiento, la hora de fin, la cantidad de horas estacionado
	//y el costo del estacionamiento (en lugar de un string se podria usar una nueva clase)
	public String finalizarEstacionamiento() {
		return sem.finalizarEstacionamiento(this.getCelular());
	}	

	@Override
	AppUsuario cambiarModo() {
		return new AppUsuarioAutomatica(this.getSem(),this.getPatente(), this.getCelular());
	}

	@Override
	void alertaInicioE() {
		// TODO Auto-generated method stub		
	}

	@Override
	void alertaFinE() {
		// TODO Auto-generated method stub		
	}
	
	
	
}
