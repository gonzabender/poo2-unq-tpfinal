package tpfinal.sistema;

import java.util.ArrayList;
import java.util.List;

import tpfinal.compras.estacionamientos.Estacionamiento;
import tpfinal.inspector.Inspector;
import tpfinal.puntoDeVenta.PuntoDeVenta;

public class ZonaSem {

	private List<PuntoDeVenta> puntosDeVenta = new ArrayList<PuntoDeVenta>();
	private SEM sem;
	@SuppressWarnings("unused")
	private Inspector inspector;

	public ZonaSem(SEM sem, List<PuntoDeVenta> puntos, Inspector inspector) {
		this.sem = sem;
		this.puntosDeVenta = puntos;
		this.inspector = inspector;
		sem.addZona(this);
	}

	public boolean estaVigente(String patente) {
		List<Estacionamiento> estacionamientos = sem.getEstacionamientosEnCurso();
		return !estacionamientos.stream().filter(e -> e.getPatente().equals(patente)).toList().isEmpty();
	}

	public List<PuntoDeVenta> getPuntosDeVenta() {
		return puntosDeVenta;
	}

}
