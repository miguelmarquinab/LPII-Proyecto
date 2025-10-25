package com.lp2.sisinventario.sistemainventario.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Integer id;

    @Column(name = "rol_nombre", nullable = false, unique = true, length = 100)
    private String nombre; // Ej.: ROLE_ADMIN, ROLE_USER

    @Column(name = "rol_estado", length = 100)
    private String estado;

    @Column(name = "rol_activo")
    private Byte activo; // opcional, si lo usas
}
