package com.lp2.sisinventario.sistemainventario.service;

import com.lp2.sisinventario.sistemainventario.dto.ModeloRequest;
import com.lp2.sisinventario.sistemainventario.dto.ModeloResponse;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface ModeloService {
    List<ModeloResponse> listar();
    ModeloResponse obtener(Integer id);
    ModeloResponse crear(ModeloRequest req);
    ModeloResponse actualizar(ModeloRequest req);
    void eliminar(Integer id);
}
