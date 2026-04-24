package com.lp2.sisinventario.sistemainventario.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "asignacionradios")
public class AsignacionRadio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asr_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dec_id", nullable = false)
    private DetalleContrato detalleContrato;

    @Column(name = "rad_id", nullable = false)
    private Integer radioId;

    @Column(name = "asr_fechaAsignacion", nullable = false)
    private LocalDate fechaAsignacion;

    @Column(name = "asr_fechaDevolucion")
    private LocalDate fechaDevolucion;

    @Column(name = "asr_estado", length = 50)
    private String estado; 

    @Column(name = "asr_observacionesAsignacion", columnDefinition = "TEXT")
    private String observacionesAsignacion;

    @Column(name = "asr_filaEliminada")
    private boolean eliminado;

    @Column(name = "usa_id")
    private int usuarioId = 1;
}