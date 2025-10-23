package com.lp2.sisinventario.sistemainventario.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "modeloRadio")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mod_id")
    private Integer id;

    @Column(name = "mod_descripcion", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "mod_codigo", nullable = false, length = 50)
    private String codigo;
}
