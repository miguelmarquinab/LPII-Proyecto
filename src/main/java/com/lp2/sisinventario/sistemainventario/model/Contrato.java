package com.lp2.sisinventario.sistemainventario.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="contratos")
public class Contrato {
    public int getId() {
		return id;
	}

	public Contrato() {
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorMensual() {
		return valorMensual;
	}

	public void setValorMensual(BigDecimal valorMensual) {
		this.valorMensual = valorMensual;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public List<DetalleContrato> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleContrato> detalles) {
		this.detalles = detalles;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "con_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cli_id", nullable = false)
    private Cliente cliente;

    @Column(name = "con_numero", nullable = false, length = 100, unique = true)
    private String numero;

    @Column(name = "con_fechaInicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "con_fechaFin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "con_estado", length = 50)
    private String estado;

    @Column(name = "con_tipoContrato", length = 50)
    private String tipoContrato;

    @Column(name = "con_valorTotal", precision = 12, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "con_valorMensual", precision = 10, scale = 2)
    private BigDecimal valorMensual;

    @Column(name = "con_observaciones", columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "con_filaEliminada")
    private boolean eliminado;

    @Column(name = "usa_id")
    private int usuarioId;
    
    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleContrato> detalles;
	
}
