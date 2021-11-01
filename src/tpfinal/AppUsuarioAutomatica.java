package tpfinal;

public class AppUsuarioAutomatica extends AppUsuario implements MovementSensor{

	public AppUsuarioAutomatica(SEM sem, String patente, int celular) {
		super(sem,patente,celular);
	}

	@Override
	AppUsuario cambiarModo() {
		return new AppUsuarioManual(this.getSem(),this.getPatente(),this.getCelular());
	}

	@Override
	void alertaInicioE() {
		
	}

	@Override
	void alertaFinE() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void driving() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void walking() {
		// TODO Auto-generated method stub
		
	}
	
	
}
