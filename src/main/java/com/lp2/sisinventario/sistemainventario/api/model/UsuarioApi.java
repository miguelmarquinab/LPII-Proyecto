package com.lp2.sisinventario.sistemainventario.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class UsuarioApi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usa_id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "clave")
    private String clave;

    @Column(name = "usa_nombres")
    private String nombres;

    @Column(name = "usa_apellidopaterno")
    private String apellidoPaterno;

    @Column(name = "usa_apellidomaterno")
    private String apellidoMaterno;

    @Column(name = "usa_estado")
    private String estado;

    @Column(name = "usa_activo")
    private String activo;

    // 🔥 RELACIÓN CLAVE
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol")
    private RolApi rol;

    public UsuarioApi() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public RolApi getRol() {
        return rol;
    }

    public void setRol(RolApi rol) {
        this.rol = rol;
    }
}
