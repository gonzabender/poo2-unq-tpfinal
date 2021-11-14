package tpfinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class EntidadAdapter implements Observer {

	private SEM sem;
	private List<Estacionamiento> interesantes = new ArrayList<Estacionamiento>();

	
	
	public EntidadAdapter(SEM sem) {
		this.sem=sem;
	}


	public void suscribirse() {
		sem.addObserver(this);
	}

	
	public void desuscribirse() {
		sem.deleteObserver(this);

	}

	@Override
	public void update(Observable o, Object arg) {
		this.interesantes.add((Estacionamiento) arg); 
	}


	public List<Estacionamiento> getInteresantes() {
		return interesantes;
	}
	
	

}
