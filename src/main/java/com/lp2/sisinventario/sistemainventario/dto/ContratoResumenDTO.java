package com.lp2.sisinventario.sistemainventario.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContratoResumenDTO {
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
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

	public BigDecimal getValorMensual() {
		return valorMensual;
	}

	public void setValorMensual(BigDecimal valorMensual) {
		this.valorMensual = valorMensual;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getNroRadios() {
		return nroRadios;
	}

	public void setNroRadios(Long nroRadios) {
		this.nroRadios = nroRadios;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public ContratoResumenDTO() {
		super();
	}

	private Integer id;
	private Integer clienteId;
	private String numero;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private BigDecimal valorMensual;
	private String estado;
	private Long nroRadios;
	private String razonSocial;

	public ContratoResumenDTO(Integer id, Integer clienteId, String numero, LocalDate fechaInicio, LocalDate fechaFin,
			BigDecimal valorMensual, String estado, Long nroRadios, String razonSocial) {
		this.id = id;
		this.clienteId = clienteId;
		this.numero = numero;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.valorMensual = valorMensual;
		this.estado = estado;
		this.nroRadios = nroRadios;
		this.razonSocial = razonSocial;
	}
}
