package com.lp2.sisinventario.sistemainventario.api.service.impl;

import com.lp2.sisinventario.sistemainventario.api.dto.CatalogoItemResponse;
import com.lp2.sisinventario.sistemainventario.api.dto.RadioRequest;
import com.lp2.sisinventario.sistemainventario.api.dto.RadioResponse;
import com.lp2.sisinventario.sistemainventario.api.repository.RadioApiRepository;
import com.lp2.sisinventario.sistemainventario.api.service.RadioApiService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RadioApiServiceImpl implements RadioApiService {

    private final RadioApiRepository radioApiRepository;

    public RadioApiServiceImpl(RadioApiRepository radioApiRepository) {
        this.radioApiRepository = radioApiRepository;
    }

    @Override
    public List<RadioResponse> listar(String texto) {
        return radioApiRepository.listar(texto);
    }

    @Override
    public RadioResponse obtener(Integer id) {
        return radioApiRepository.obtener(id);
    }

    @Override
    public Integer crear(RadioRequest request) {
        return radioApiRepository.guardar(request);
    }

    @Override
    public Integer actualizar(Integer id, RadioRequest request) {
        request.setId(id);
        return radioApiRepository.guardar(request);
    }

    @Override
    public void eliminar(Integer id) {
        radioApiRepository.eliminar(id);
    }

    @Override
    public List<CatalogoItemResponse> listarModelos() {
        return radioApiRepository.listarModelos();
    }

    @Override
    public List<CatalogoItemResponse> listarEstados() {
        return radioApiRepository.listarEstados();
    }
}
