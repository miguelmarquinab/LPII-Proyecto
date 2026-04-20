package com.lp2.sisinventario.sistemainventario.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class RolApi {
    @Id
    @Column(name = "rol_id")
    private Integer id;

    @Column(name = "rol_nombre")
    private String rolNombre;

    @Column(name = "rol_estado")
    private String rolEstado;

    @Column(name = "rol_activo")
    private Boolean rolActivo;

    public RolApi() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public String getRolEstado() {
        return rolEstado;
    }

    public void setRolEstado(String rolEstado) {
        this.rolEstado = rolEstado;
    }

    public Boolean getRolActivo() {
        return rolActivo;
    }

    public void setRolActivo(Boolean rolActivo) {
        this.rolActivo = rolActivo;
    }
}
