package com.lp2.sisinventario.sistemainventario.service;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.lp2.sisinventario.sistemainventario.dto.ContratoFiltroDTO;
import com.lp2.sisinventario.sistemainventario.dto.ContratoResumenDTO;
import com.lp2.sisinventario.sistemainventario.model.Contrato;
import com.lp2.sisinventario.sistemainventario.repository.ContratoRepository;

@Service
@RequiredArgsConstructor
public class ContratoService {
	
	private final ContratoRepository contratoRepository;
	
	public List<ContratoResumenDTO> obtenerResumenContratos(ContratoFiltroDTO filtro) {
		return contratoRepository.buscarTodosParaResumen();
	}
	
	public Contrato getContratoById(Integer id) {
		return contratoRepository.findByIdWithRelation(id).orElse(null);
	}
}