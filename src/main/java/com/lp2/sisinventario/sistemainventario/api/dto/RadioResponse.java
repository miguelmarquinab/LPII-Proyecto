package com.lp2.sisinventario.sistemainventario.api.dto;

public class RadioResponse {

    private Integer id;
    private Integer modId;
    private Integer esrId;
    private String modCodigo;
    private String modelo;
    private String estado;
    private String serie;
    private String fechaIngreso;
    private Integer activo;

    public RadioResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModId() {
        return modId;
    }

    public void setModId(Integer modId) {
        this.modId = modId;
    }

    public Integer getEsrId() {
        return esrId;
    }

    public void setEsrId(Integer esrId) {
        this.esrId = esrId;
    }

    public String getModCodigo() {
        return modCodigo;
    }

    public void setModCodigo(String modCodigo) {
        this.modCodigo = modCodigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }
}
