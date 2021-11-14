package tpfinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class EntidadAdapter implements Observer {

	private SEM sem;
	private ArrayList<String> informes = new ArrayList<String>();

	
	
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
		this.informes.add((String) arg); 
	}


	public List<String> getInformes() {
		return informes;
	}
	
	

}
