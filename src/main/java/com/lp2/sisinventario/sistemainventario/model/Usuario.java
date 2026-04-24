package com.lp2.sisinventario.sistemainventario.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usa_id")
    private Integer id;

    // username (login)
    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String username;

    // password (BCrypt)
    @Column(name = "clave", nullable = false, length = 100)
    private String password;

    // tu columna es VARCHAR; lo convertimos a boolean con un converter

    @Convert(converter = VarcharBooleanConverter.class)
    @Column(name = "usa_activo", length = 100)
    @Convert(converter = com.lp2.sisinventario.sistemainventario.model.VarcharBooleanConverter.class)
    private Boolean enabled;

    // muchos usuarios -> un rol (columna "rol" FK a roles.rol_id)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol", referencedColumnName = "rol_id")
    private Rol rol;

    // --- columnas adicionales si luego quieres usarlas ---
    @Column(name = "usa_nombres", length = 100) private String nombres;
    @Column(name = "usa_apellidopaterno", length = 100) private String apellidoPaterno;
    @Column(name = "usa_apellidomaterno", length = 100) private String apellidoMaterno;
    @Column(name = "usa_estado", length = 100) private String estado;
    @Column(name = "usa_genero", length = 100) private String genero;
}
