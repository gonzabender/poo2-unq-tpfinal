package tpfinal.sistema;

import java.util.List;
import tpfinal.app.usuario.Celular;
import tpfinal.compras.Compra;
import tpfinal.compras.estacionamientos.Estacionamiento;
import tpfinal.compras.estacionamientos.EstacionamientoApp;
import tpfinal.inspector.Infraccion;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class SEM {

	/**
	 * Esta clase representa al sistema general de estacionamiento medido
	 */

	private List<ZonaSem> zonas;
	private List<Estacionamiento> estacionamientosEnCurso;
	private List<Compra> compras;
	private List<Infraccion> infracciones;
	private LocalTime horaActual;
	private HashMap<Celular, String> celularesEstacionados;
	private int precioHora;
	private ArrayList<EntidadAdapter> entidades;
	private FranjaHoraria franjaHoraria;

	public SEM() {
		this.compras = new ArrayList<Compra>();
		this.estacionamientosEnCurso = new ArrayList<Estacionamiento>();
		this.zonas = new ArrayList<ZonaSem>();
		this.infracciones = new ArrayList<Infraccion>();
		this.celularesEstacionados = new HashMap<Celular, String>();
		this.precioHora = 40;
		this.entidades = new ArrayList<EntidadAdapter>();
	}

	public void addZona(ZonaSem z) {
		zonas.add(z);
	}

	public void addEstacionamiento(Estacionamiento estacionamiento) {
		estacionamientosEnCurso.add(estacionamiento);
		this.notificarEntidadesEstacionamientoIniciado(estacionamiento);
	}

	private void notificarEntidadesEstacionamientoIniciado(Estacionamiento estacionamiento) {
		this.entidades.stream().forEach(entidad -> entidad.actualizarInicioEstacionamiento(estacionamiento));
	}

	public void addInfraccion(Infraccion i) {
		this.infracciones.add(i);
	}

	public void addCompra(Compra compra) {
		this.compras.add(compra);
	}

	/**
	 * 
	 * @param celular El celular del cliente.
	 * @param patente La patente del cliente.
	 * @param hora    La hora de inicio del estacionamiento.
	 * @return Informacion de la hora de inicio y de fin, o un mensaje de error si
	 *         el celular no posee suficiente saldo.
	 */
	public String iniciarEstacionamiento(Celular celular, String patente, LocalTime hora) {
		return this.franjaHoraria.iniciarEstacionamiento(this, celular, patente, hora);
	}

	private boolean estaEnFranjaHoraria() {
		int hora = this.horaActual.getHour();
		return hora < 20 && hora >= 7;
	}

	/**
	 * 
	 * @param celular      De tipo Celular
	 * @param horaDeCompra de tipo int
	 * @return Un n?mero que representa la hora de finalizaci?n de un
	 *         estacionamiento
	 */
	public LocalTime calcularFinalDeEstacionamiento(Celular celular) {
		int saldo = celular.getSaldo();
		LocalTime horas = this.horaActual.plusHours(saldo / this.precioHora);

		return horas;
	}

	/**
	 * 
	 * @param celular El n?mero del celular del cliente, lo unico requerido para
	 *                finalizar su estacionamiento.
	 * @return Informaci?n variada sobre el servicio otorgado
	 */
	public String finalizarEstacionamiento(Celular celular) {
		String patente = this.celularesEstacionados.get(celular);
		Estacionamiento estacionamiento = this.estacionamientosEnCurso.stream()
				.filter(est -> est.getPatente() == patente).findFirst().get(); // Si no estaciono esto falla, hay que
		// tocarlo para preguntar si estaciono antes
		estacionamiento.setHorarioFin(horaActual);

		this.estacionamientosEnCurso.remove(estacionamiento);
		this.descontarCr?dito(celular, this.precioHora, estacionamiento);

		String info = this.retornarInfo(estacionamiento.getHorarioInicio(), estacionamiento.getHorarioFin(),
				estacionamiento.duraci?n(), celular.getSaldo());

		this.notificarEntidadesEstacionamientoFinalizado(estacionamiento);

		return info;
	}

	private void notificarEntidadesEstacionamientoFinalizado(Estacionamiento estacionamiento) {
		this.entidades.stream().forEach(entidad -> entidad.actualizarFinEstacionamiento(estacionamiento));
	}

	public String retornarInfo(LocalTime inicio, LocalTime fin, int duraci?n, int cr?dito) {
		return "Hora de Inicio: " + String.valueOf(inicio) + "hs. Hora de fin: " + String.valueOf(fin)
				+ "hs. Duraci?n: " + String.valueOf(duraci?n) + "hs. Cr?dito restante: " + String.valueOf(cr?dito);
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public List<Estacionamiento> getEstacionamientosEnCurso() {
		return estacionamientosEnCurso;
	}

	/**
	 * Descuenta saldo al celular
	 * 
	 * @param celular Celular a descontar saldo
	 * @param monto   Monto a descontar
	 * @param est     Se necesita la hora de inicio para compararla con la hora
	 *                actual y asi descontar
	 */
	private void descontarCr?dito(Celular celular, int monto, Estacionamiento est) {
		celular.restarSaldo(((est.getHorarioFin().getHour() - est.getHorarioInicio().getHour()) * monto));
	}

	public void finalizarTodosLosEstacionamientos() {
		this.franjaHoraria.finalizarTodosLosEstacionamientos(this);
	}

	public List<Infraccion> getInfracciones() {
		return infracciones;
	}

	public LocalTime getHoraActual() {
		return horaActual;
	}

	public void setHoraActual(LocalTime horaActual) {
		this.horaActual = horaActual;
		this.verificarVigencias(horaActual);

		if (!this.estaEnFranjaHoraria()) {
			this.franjaHoraria = FranjaHoraria.Inactiva;
			this.finalizarTodosLosEstacionamientos();
		} else {
			this.franjaHoraria = FranjaHoraria.Activa;
		}
	}

	private void verificarVigencias(LocalTime horaActual) {
		List<Estacionamiento> e = this.estacionamientosEnCurso.stream()
				.filter(est -> est.getHorarioFin() == horaActual).toList();
		this.estacionamientosEnCurso.removeAll(e);
		this.notificarTodosLosFinales(e);
	}

	public boolean tieneSaldoSuficiente(Celular cel) {
		return cel.getSaldo() >= this.precioHora;
	}

	public void subscribirEntidad(EntidadAdapter entidad) {
		this.entidades.add(entidad);
	}

	public ArrayList<EntidadAdapter> getEntidades() {
		return this.entidades;
	}

	public void desubscribirEntidad(EntidadAdapter callCenter) {
		this.entidades.remove(callCenter);
	}

	public void notificarTodosLosFinales(List<Estacionamiento> est) {
		for (Estacionamiento e : est) {
			this.notificarEntidadesEstacionamientoFinalizado(e);
		}
	}

	public String estacionamientoPermitido(Celular celular, String patente, LocalTime hora) {
		LocalTime finDeEstacionamiento = this.calcularFinalDeEstacionamiento(celular);
		EstacionamientoApp operaci?n = new EstacionamientoApp(patente, celular, this.getHoraActual());
		String info = "Su estacionamiento es valido desde las " + String.valueOf(hora) + "hs. " + "Hasta las "
				+ String.valueOf(finDeEstacionamiento) + "hs.";
		this.addEstacionamiento(operaci?n);
		this.celularesEstacionados.put(celular, patente);
		return info;
	}

	public void finalizarTodosLosEstacionamientosPermitido() {
		List<Estacionamiento> estacionamientos = this.estacionamientosEnCurso;

		for (Estacionamiento e : estacionamientos) {
			e.terminarEstacionamiento();
			this.notificarEntidadesEstacionamientoFinalizado(e);
		}
		this.estacionamientosEnCurso.removeAll(estacionamientos);
	}

	public List<ZonaSem> getZonas() {
		return zonas;
	}

}
