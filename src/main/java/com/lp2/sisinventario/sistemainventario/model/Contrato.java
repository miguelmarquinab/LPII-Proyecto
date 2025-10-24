package com.lp2.sisinventario.sistemainventario.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="contratos")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "con_id")
    private int id;

    @Column(name = "cli_id", nullable = false)
    private int clienteId;

    @Column(name = "con_numero", nullable = false, length = 100, unique = true)
    private String numero;

    @Column(name = "con_fechaInicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "con_fechaFin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "con_estado", length = 50)
    private String estado;

    @Column(name = "con_tipoContrato", length = 50)
    private String tipoContrato;

    @Column(name = "con_valorTotal", precision = 12, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "con_valorMensual", precision = 10, scale = 2)
    private BigDecimal valorMensual;

    @Column(name = "con_observaciones", columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "con_filaEliminada")
    private boolean eliminado;

    @Column(name = "usa_id")
    private int usuarioId;
    
    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleContrato> detalles;
	
}
