package com.lp2.sisinventario.sistemainventario.api.controller;

import com.lp2.sisinventario.sistemainventario.api.dto.CatalogoItemResponse;
import com.lp2.sisinventario.sistemainventario.api.dto.RadioRequest;
import com.lp2.sisinventario.sistemainventario.api.dto.RadioResponse;
import com.lp2.sisinventario.sistemainventario.api.service.RadioApiService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/radios")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class RadioApiController {

    private final RadioApiService radioApiService;

    @GetMapping
    public ResponseEntity<List<RadioResponse>> listar(
            @RequestParam(required = false, defaultValue = "") String texto) {
        List<RadioResponse> lista = radioApiService.listar(texto);
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RadioResponse> obtener(@PathVariable Integer id) {
        try {
            RadioResponse radio = radioApiService.obtener(id);
            return ResponseEntity.ok(radio);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Integer> crear(@RequestBody RadioRequest request) {
        Integer nuevoId = radioApiService.crear(request);
        return ResponseEntity.ok(nuevoId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> actualizar(
            @PathVariable Integer id,
            @RequestBody RadioRequest request) {
        Integer radioId = radioApiService.actualizar(id, request);
        return ResponseEntity.ok(radioId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        radioApiService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/catalogos/modelos")
    public ResponseEntity<List<CatalogoItemResponse>> listarModelos() {
        List<CatalogoItemResponse> modelos = radioApiService.listarModelos();
        return ResponseEntity.ok(modelos);
    }

    @GetMapping("/catalogos/estados")
    public ResponseEntity<List<CatalogoItemResponse>> listarEstados() {
        List<CatalogoItemResponse> estados = radioApiService.listarEstados();
        return ResponseEntity.ok(estados);
    }
}