package com.lp2.sisinventario.sistemainventario.api.repository;

import com.lp2.sisinventario.sistemainventario.api.dto.CatalogoItemResponse;
import com.lp2.sisinventario.sistemainventario.api.dto.RadioRequest;
import com.lp2.sisinventario.sistemainventario.api.dto.RadioResponse;

import java.util.List;

public interface RadioApiRepository {

    List<RadioResponse> listar(String texto);
    RadioResponse obtener(Integer id);
    Integer guardar(RadioRequest request);
    void eliminar(Integer id);
    List<CatalogoItemResponse> listarModelos();
    List<CatalogoItemResponse> listarEstados();
}
