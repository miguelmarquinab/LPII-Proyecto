package com.lp2.sisinventario.sistemainventario.api.dto;


public class RadioRequest {
    private Integer id;
    private Integer modId;
    private Integer esrId;
    private String serie;
    private String fechaIngreso;
    private Integer activo;

    public RadioRequest() {
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
