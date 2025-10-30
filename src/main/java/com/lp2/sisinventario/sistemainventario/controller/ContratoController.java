package com.lp2.sisinventario.sistemainventario.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lp2.sisinventario.sistemainventario.dto.ContratoFiltroDTO;
import com.lp2.sisinventario.sistemainventario.dto.ContratoResumenDTO;
import com.lp2.sisinventario.sistemainventario.model.Contrato;
import com.lp2.sisinventario.sistemainventario.service.ClienteService;
import com.lp2.sisinventario.sistemainventario.service.ContratoService;

@Controller
@RequestMapping("/contrato")
public class ContratoController {
	
	@Autowired
	private ContratoService contratoService;
	
	@Autowired
	private ClienteService clienteService;
	
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
		filtros.setFechaFin(fechaIni);
		
		
		List<ContratoResumenDTO> contratos = contratoService.obtenerResumenContratos(filtros);
		
		
		model.addAttribute("contratos", contratos);
		model.addAttribute("clientes", clienteService.getClientes());
		return "contrato/contrato.html";
	}
	
	@GetMapping("/nuevo")
	public String nuevoContrato(Model model) {
		model.addAttribute("contrato", new Contrato());
		model.addAttribute("titulo", "Nuevo contrato");
		return "contrato/contrato-form.html";
	}
	
	/*TODO: Pendiente*/
	@GetMapping("/editar/{id}")
	public String editarContrato(@PathVariable Integer id,Model model) {
		model.addAttribute("contrato", contratoService.getContratoById(id));
		model.addAttribute("clientes", clienteService.getClientes());
		model.addAttribute("titulo", "Editar contrato");
		return "contrato/contrato-form.html";
	}
}
