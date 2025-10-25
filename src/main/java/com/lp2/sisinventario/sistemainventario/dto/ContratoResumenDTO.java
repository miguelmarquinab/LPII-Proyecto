package com.lp2.sisinventario.sistemainventario.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContratoResumenDTO {
    private Integer id;
    private Integer clienteId;
    private String numero;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal valorMensual;
    private String estado;
    private Integer nroRadios;
    private String razonSocial;
}
