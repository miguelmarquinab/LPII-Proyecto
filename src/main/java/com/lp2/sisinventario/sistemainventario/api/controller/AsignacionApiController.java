package com.lp2.sisinventario.sistemainventario.api.controller;

import com.lp2.sisinventario.sistemainventario.model.AsignacionRadio;
import com.lp2.sisinventario.sistemainventario.dto.AsignacionResumenDTO;
import com.lp2.sisinventario.sistemainventario.repository.AsignacionRadioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RestController
@RequestMapping("/api/asignaciones")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AsignacionApiController {

    private final AsignacionRadioRepository asignacionRepository;

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<AsignacionResumenDTO>> listarAsignaciones() {
        List<AsignacionResumenDTO> lista = asignacionRepository.listarAsignacionesResumen();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsignacionRadio> obtenerAsignacion(@PathVariable Integer id) {
        return asignacionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AsignacionRadio> crearAsignacion(@RequestBody AsignacionRadio asignacion) {
        asignacion.setEliminado(false);
        if (asignacion.getEstado() == null) {
            asignacion.setEstado("ASIGNADO");
        }
        AsignacionRadio nuevaAsignacion = asignacionRepository.save(asignacion);
        return ResponseEntity.ok(nuevaAsignacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsignacionRadio> actualizarAsignacion(@PathVariable Integer id, @RequestBody AsignacionRadio asignacion) {
        if (!asignacionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        asignacion.setId(id);
        AsignacionRadio asignacionActualizada = asignacionRepository.save(asignacion);
        return ResponseEntity.ok(asignacionActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAsignacion(@PathVariable Integer id) {
        return asignacionRepository.findById(id).map(asignacion -> {
            asignacion.setEliminado(true);
            asignacionRepository.save(asignacion);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}