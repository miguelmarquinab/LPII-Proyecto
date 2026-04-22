package com.lp2.sisinventario.sistemainventario.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.lp2.sisinventario.sistemainventario.dto.ContratoFiltroDTO;
import com.lp2.sisinventario.sistemainventario.dto.ContratoResumenDTO;
import com.lp2.sisinventario.sistemainventario.model.Contrato;
import com.lp2.sisinventario.sistemainventario.service.ClienteService;
import com.lp2.sisinventario.sistemainventario.service.ContratoService;
import com.lp2.sisinventario.sistemainventario.service.impl.ModeloServiceImpl;

@Controller
@RequestMapping("/contrato")
@RequiredArgsConstructor
public class ContratoController {
	
	private final ContratoService contratoService;
	private final ClienteService clienteService;
	private final ModeloServiceImpl modeloServiceImpl;
	
	@GetMapping
	public String getContratos(@RequestParam(required = false) String estado,
	        @RequestParam(required = false) Integer idCliente,
	        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaIni,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin,
	        Model model) {
		
		ContratoFiltroDTO filtros = new ContratoFiltroDTO();
		filtros.setClienteId(idCliente);
		filtros.setEstado(estado);
		filtros.setFechaFin(fechaFin);
		filtros.setFechaInicio(fechaIni); 
		
		List<ContratoResumenDTO> contratos = contratoService.obtenerResumenContratos(filtros);
		
		model.addAttribute("contratos", contratos);
		model.addAttribute("clientes", clienteService.getClientes());
		return "contrato/contrato.html";
	}
	
	@GetMapping("/nuevo")
	public String nuevoContrato(Model model) {
		model.addAttribute("contrato", new Contrato());
		model.addAttribute("titulo", "Nuevo contrato");
		model.addAttribute("clientes", clienteService.getClientes());
		model.addAttribute("modelos", modeloServiceImpl.listar());
		model.addAttribute("listaDetalleContrato", new ArrayList<>());
		return "contrato/contrato-form.html";
	}
	
	@GetMapping("/editar/{id}")
	public String editarContrato(@PathVariable Integer id,Model model) {
		
		Contrato contratoSeleccionado = contratoService.getContratoById(id);
		
		model.addAttribute("contrato", contratoSeleccionado);
		model.addAttribute("clienteIdSeleccionado", contratoSeleccionado.getCliente().getId());
			
		model.addAttribute("clientes", clienteService.getClientes());
		model.addAttribute("modelos", modeloServiceImpl.listar());
		model.addAttribute("listaDetalleContrato", contratoSeleccionado.getDetalles());
			
		model.addAttribute("titulo", "Editar contrato");
		return "contrato/contrato-form.html";
	}
}