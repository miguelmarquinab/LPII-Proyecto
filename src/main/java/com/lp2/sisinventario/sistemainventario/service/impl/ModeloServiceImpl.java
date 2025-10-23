package com.lp2.sisinventario.sistemainventario.service.impl;

import com.lp2.sisinventario.sistemainventario.dto.ModeloRequest;
import com.lp2.sisinventario.sistemainventario.dto.ModeloResponse;
import com.lp2.sisinventario.sistemainventario.model.Modelo;
import com.lp2.sisinventario.sistemainventario.repository.ModeloRepository;
import com.lp2.sisinventario.sistemainventario.service.ModeloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service                         // <-- FALTABA
@RequiredArgsConstructor         // <-- FALTABA (inyecta el final repo)
@Transactional                   // (opcional pero recomendado a nivel de servicio)
public class ModeloServiceImpl implements ModeloService {

    private final ModeloRepository repo;

    @Override
    @Transactional(readOnly = true)
    public List<ModeloResponse> listar() {
        return repo.findAll().stream()
                .map(m -> new ModeloResponse(m.getId(), m.getDescripcion(), m.getCodigo()))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ModeloResponse obtener(Integer id) {
        Modelo m = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Modelo no existe"));
        return new ModeloResponse(m.getId(), m.getDescripcion(), m.getCodigo());
    }

    @Override
    public ModeloResponse crear(ModeloRequest req) {
        if (repo.existsByCodigoIgnoreCase(req.getCodigo())) {
            throw new IllegalArgumentException("Código de modelo ya existe");
        }
        Modelo m = Modelo.builder()
                .descripcion(req.getDescripcion())
                .codigo(req.getCodigo())
                .build();
        m = repo.save(m);
        return new ModeloResponse(m.getId(), m.getDescripcion(), m.getCodigo());
    }

    @Override
    public ModeloResponse actualizar(ModeloRequest req) {
        Modelo m = repo.findById(req.getId()).orElseThrow(() -> new IllegalArgumentException("Modelo no existe"));
        m.setDescripcion(req.getDescripcion());
        m.setCodigo(req.getCodigo());
        m = repo.save(m);
        return new ModeloResponse(m.getId(), m.getDescripcion(), m.getCodigo());
    }

    @Override
    public void eliminar(Integer id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Modelo no existe");
        repo.deleteById(id);
    }
}
