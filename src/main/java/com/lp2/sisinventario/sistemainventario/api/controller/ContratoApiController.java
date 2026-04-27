package com.lp2.sisinventario.sistemainventario.api.controller;

import com.lp2.sisinventario.sistemainventario.model.Contrato;
import com.lp2.sisinventario.sistemainventario.dto.ContratoResumenDTO;
import com.lp2.sisinventario.sistemainventario.repository.ContratoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional; 

import java.util.List;

@RestController
@RequestMapping("/api/contratos")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ContratoApiController {

    private final ContratoRepository contratoRepository;

    @GetMapping
    public ResponseEntity<List<ContratoResumenDTO>> listarContratos() {
        List<ContratoResumenDTO> lista = contratoRepository.buscarTodosParaResumen();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<Contrato> obtenerContrato(@PathVariable Integer id) {
        return contratoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Contrato> crearContrato(@RequestBody Contrato contrato) {
        contrato.setEliminado(false);
        if (contrato.getEstado() == null) contrato.setEstado("ACTIVO");
        
        Contrato nuevoContrato = contratoRepository.save(contrato);
        return ResponseEntity.ok(nuevoContrato); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrato> actualizarContrato(@PathVariable Integer id, @RequestBody Contrato contrato) {
        if (!contratoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        contrato.setId(id);
        Contrato contratoActualizado = contratoRepository.save(contrato);
        return ResponseEntity.ok(contratoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContrato(@PathVariable Integer id) {
        if (!contratoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        contratoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}