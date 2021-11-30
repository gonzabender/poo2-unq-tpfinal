package tpfinal.sistema;

import java.util.ArrayList;
import java.util.List;

import tpfinal.compras.estacionamientos.Estacionamiento;
import tpfinal.inspector.Inspector;
import tpfinal.puntoDeVenta.PuntoDeVenta;

public class ZonaSem {

	private List<PuntoDeVenta> puntosDeVenta = new ArrayList<PuntoDeVenta>();
	private List<Estacionamiento> estacionamientos = new ArrayList<Estacionamiento>();
	private SEM sem;
	private Inspector inspector;

	public ZonaSem(SEM sem, List<PuntoDeVenta> puntos, Inspector inspector) {
		this.sem = sem;
		this.puntosDeVenta = puntos;
		this.inspector = inspector;
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

	public Inspector getInspector() {
		return inspector;
	}

	public void agregarPuntoDeVenta(PuntoDeVenta punto) {
		this.puntosDeVenta.add(punto);
	}

	/*
	 * public boolean estaVigente(String patente) { boolean res = false; for
	 * (Estacionamiento e : sem.getEstacionamientosEnCurso()) { if (e.getPatente()
	 * == patente) { res = true; break; } } return res; }
	 */

	public boolean estaVigente(String patente) {
		List<Estacionamiento> estacionamientos = sem.getEstacionamientosEnCurso();
		long e = estacionamientos.stream()
				.filter(est -> est.getPatente() == patente).count();
		return e == 1;
	}

}
