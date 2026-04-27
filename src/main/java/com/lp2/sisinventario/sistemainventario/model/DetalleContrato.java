package com.lp2.sisinventario.sistemainventario.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detalleContratos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DetalleContrato {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dec_id")
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "con_id", nullable = false)
    private Contrato contrato;

    @Column(name = "mod_id", nullable = false)
    private int modeloId;

    @Column(name = "dec_cantidad", nullable = false)
    private int cantidad;

    @Column(name = "dec_precioUnitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "dec_subtotal", nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "dec_filaEliminada", nullable = false)
    private boolean eliminado;

    @Column(name = "usa_id")
    private int usuarioId;
}