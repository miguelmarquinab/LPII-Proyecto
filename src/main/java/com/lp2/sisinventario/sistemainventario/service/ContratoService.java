package com.lp2.sisinventario.sistemainventario.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp2.sisinventario.sistemainventario.dto.ContratoResumenDTO;
import com.lp2.sisinventario.sistemainventario.repository.ContratoRepository;

@Service
public class ContratoService {
	
	@Autowired
	ContratoRepository contratoRepository;
	
	public List<ContratoResumenDTO> obtenerResumenContratos(String estado, int clienteId, String fechaInicio, String fechaFin) {
	    List<Object[]> resultados = contratoRepository.buscarContratosResumen(estado, clienteId, fechaInicio, fechaFin);
	    
	    return resultados.stream()
	        .map(r -> new ContratoResumenDTO(
	        		
	        		/*
	            ((Integer) r[0]).intValue(),
	            ((Integer) r[1]).intValue(),
	            (String) r[2],
	            ((Date) r[3]).toLocalDate(),
	            ((Date) r[4]).toLocalDate(),
	            (BigDecimal) r[5],
	            (String) r[6],
	            ((Integer) r[7]).intValue(),
	            (String) r[8]
	            */
	        ))
	        .toList();
	}
	
}
