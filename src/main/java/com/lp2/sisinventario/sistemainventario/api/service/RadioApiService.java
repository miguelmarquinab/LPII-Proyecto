package com.lp2.sisinventario.sistemainventario.api.service;

import com.lp2.sisinventario.sistemainventario.api.dto.CatalogoItemResponse;
import com.lp2.sisinventario.sistemainventario.api.dto.RadioRequest;
import com.lp2.sisinventario.sistemainventario.api.dto.RadioResponse;

import java.util.List;

public interface RadioApiService {

    List<RadioResponse> listar(String texto);
    RadioResponse obtener(Integer id);
    Integer crear(RadioRequest request);
    Integer actualizar(Integer id, RadioRequest request);
    void eliminar(Integer id);
    List<CatalogoItemResponse> listarModelos();
    List<CatalogoItemResponse> listarEstados();
}
