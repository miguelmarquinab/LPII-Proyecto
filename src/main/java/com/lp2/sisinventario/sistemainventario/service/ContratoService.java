package com.lp2.sisinventario.sistemainventario.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp2.sisinventario.sistemainventario.dto.ContratoFiltroDTO;
import com.lp2.sisinventario.sistemainventario.dto.ContratoResumenDTO;
import com.lp2.sisinventario.sistemainventario.model.Contrato;
import com.lp2.sisinventario.sistemainventario.repository.ContratoRepository;

@Service
public class ContratoService {
	
	@Autowired
	ContratoRepository contratoRepository;
	
	public List<ContratoResumenDTO> obtenerResumenContratos(ContratoFiltroDTO filtro) {
		
        System.out.println("===*********************************** DEBUG FILTRO ===");
        System.out.println("ClienteId recibido: " + filtro.getClienteId());
        System.out.println("Estado recibido: " + filtro.getEstado());
        System.out.println("FechaInicio recibida: " + filtro.getFechaInicio());
        System.out.println("FechaFin recibida: " + filtro.getFechaFin());
	    
		Integer clienteId = (filtro.getClienteId() != null && filtro.getClienteId() == 0) ? null : filtro.getClienteId();
		String estado = (filtro.getEstado() != null && filtro.getEstado().isEmpty()) ? null : filtro.getEstado();
		
		List<ContratoResumenDTO> resultados = contratoRepository.buscarContratosResumen(estado, clienteId, filtro.getFechaInicio(), filtro.getFechaFin());
	    return resultados;
	}
	
	public Contrato getContratoById(Integer id) {
		return contratoRepository.findById(id).orElse(null);
	}
	
}
