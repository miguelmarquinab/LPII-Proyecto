package com.lp2.sisinventario.sistemainventario.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AsignacionResumenDTO {
    private Integer id;
    private String numeroContrato;
    private Integer radioId;
    private LocalDate fechaAsignacion;
    private LocalDate fechaDevolucion;
    private String estado;
}