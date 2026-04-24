package com.lp2.sisinventario.sistemainventario.api.controller;

import com.lp2.sisinventario.sistemainventario.model.AsignacionRadio;
import com.lp2.sisinventario.sistemainventario.dto.AsignacionResumenDTO;
import com.lp2.sisinventario.sistemainventario.repository.AsignacionRadioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/asignaciones")
@CrossOrigin(origins = "*")
public class AsignacionApiController {

    private final AsignacionRadioRepository asignacionRepository;

    public AsignacionApiController(AsignacionRadioRepository asignacionRepository) {
        this.asignacionRepository = asignacionRepository;
    }

    @GetMapping
    public ResponseEntity<List<AsignacionResumenDTO>> listarAsignaciones() {
        List<AsignacionResumenDTO> lista = asignacionRepository.listarAsignacionesResumen();
        
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
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

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevaAsignacion.getId())
                .toUri();

        return ResponseEntity.created(location).body(nuevaAsignacion); // 201 Created
    }

    // 4. ACTUALIZAR (PUT) - Ideal para registrar la fecha de devolución
    @PutMapping("/{id}")
    public ResponseEntity<AsignacionRadio> actualizarAsignacion(@PathVariable Integer id, @RequestBody AsignacionRadio asignacion) {
        if (!asignacionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        asignacion.setId(id);
        AsignacionRadio actualizada = asignacionRepository.save(asignacion);
        return ResponseEntity.ok(actualizada);
    }

    // 5. ELIMINAR (DELETE) - Eliminación lógica
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAsignacion(@PathVariable Integer id) {
        return asignacionRepository.findById(id).map(asignacion -> {
            asignacion.setEliminado(true); // Solo cambiamos la bandera, no hacemos DROP
            asignacionRepository.save(asignacion);
            return ResponseEntity.noContent().<Void>build(); // 204 No Content
        }).orElse(ResponseEntity.notFound().build());
    }
}