package tpfinal;

import java.util.ArrayList;
import java.util.List;

public class ZonaSem {
		
	List<PuntoDeVenta> puntosDeVenta = new ArrayList<PuntoDeVenta>();
	List<Estacionamiento> estacionamientos = new ArrayList<Estacionamiento>();
	SEM sem;
	
	public ZonaSem(SEM sem, ArrayList<PuntoDeVenta> puntos) {
		this.sem = sem;
		this.puntosDeVenta = puntos;
	}
		
	public List<PuntoDeVenta> getPuntosDeVenta() {
		return puntosDeVenta;
	}

	public void setPuntosDeVenta(List<PuntoDeVenta> puntosDeVenta) {
		this.puntosDeVenta = puntosDeVenta;
	}


	public List<Estacionamiento> getEstacionamientos() {
		return estacionamientos;
	}


	public void setEstacionamientos(List<Estacionamiento> estacionamientos) {
		this.estacionamientos = estacionamientos;
	}

	public SEM getSem() {
		return sem;
	}

	
	public void setSem(SEM sem) {
		this.sem = sem;
	}
	
	public void agregarPuntoDeVenta(PuntoDeVenta punto) {
		this.puntosDeVenta.add(punto);
	}
	
	public boolean estaVigente(String patente) {
		boolean res = false;
		for(Estacionamiento e : this.estacionamientos) {
			if (e.getPatente() == patente) {
				res = true;
				break;
			}
		}
		return res;
	}
	
	
	
}
