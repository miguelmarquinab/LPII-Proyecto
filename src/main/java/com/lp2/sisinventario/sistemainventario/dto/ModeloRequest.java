package com.lp2.sisinventario.sistemainventario.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ModeloRequest {
    private Integer id;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotBlank(message = "El código es obligatorio")
    private String codigo;
}
