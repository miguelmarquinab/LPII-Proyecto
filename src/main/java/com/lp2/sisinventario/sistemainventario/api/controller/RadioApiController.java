package com.lp2.sisinventario.sistemainventario.api.controller;

import com.lp2.sisinventario.sistemainventario.api.dto.ApiResponse;
import com.lp2.sisinventario.sistemainventario.api.dto.CatalogoItemResponse;
import com.lp2.sisinventario.sistemainventario.api.dto.RadioRequest;
import com.lp2.sisinventario.sistemainventario.api.dto.RadioResponse;
import com.lp2.sisinventario.sistemainventario.api.service.RadioApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/radios")
public class RadioApiController {
    private final RadioApiService radioApiService;

    public RadioApiController(RadioApiService radioApiService) {
        this.radioApiService = radioApiService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RadioResponse>>> listar(
            @RequestParam(required = false, defaultValue = "") String texto) {
        return ResponseEntity.ok(
                ApiResponse.ok("Listado de radios correcto", radioApiService.listar(texto))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RadioResponse>> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(
                ApiResponse.ok("Radio obtenido correctamente", radioApiService.obtener(id))
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> crear(@RequestBody RadioRequest request) {
        Integer id = radioApiService.crear(request);
        return ResponseEntity.ok(
                ApiResponse.ok("Radio registrado correctamente", id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Integer>> actualizar(
            @PathVariable Integer id,
            @RequestBody RadioRequest request) {
        Integer radioId = radioApiService.actualizar(id, request);
        return ResponseEntity.ok(
                ApiResponse.ok("Radio actualizado correctamente", radioId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Integer id) {
        radioApiService.eliminar(id);
        return ResponseEntity.ok(
                ApiResponse.ok("Radio eliminado correctamente", null)
        );
    }

    @GetMapping("/catalogos/modelos")
    public ResponseEntity<ApiResponse<List<CatalogoItemResponse>>> listarModelos() {
        return ResponseEntity.ok(
                ApiResponse.ok("Modelos obtenidos correctamente", radioApiService.listarModelos())
        );
    }

    @GetMapping("/catalogos/estados")
    public ResponseEntity<ApiResponse<List<CatalogoItemResponse>>> listarEstados() {
        return ResponseEntity.ok(
                ApiResponse.ok("Estados obtenidos correctamente", radioApiService.listarEstados())
        );
    }
}
