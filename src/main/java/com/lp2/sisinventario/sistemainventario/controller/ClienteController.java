package com.lp2.sisinventario.sistemainventario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.lp2.sisinventario.sistemainventario.model.Cliente;
import com.lp2.sisinventario.sistemainventario.service.ClienteService;

@Controller
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {
	
	private final ClienteService clienteService;
	
	@GetMapping
	public String getClientes(Model model){
		model.addAttribute("clientes", clienteService.getClientes());
		return "clientes/clientes.html";
	}
	
	@GetMapping("/nuevo")
	public String nuevoCliente(Model model){
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("titulo", "Nuevo cliente");
		return "clientes/clientes-form.html";
	}
	
	@GetMapping("/editar/{id}")
	public String editarCliente(@PathVariable Integer id, Model model){
		model.addAttribute("cliente", clienteService.getClienteById(id));
		model.addAttribute("titulo", "Editar cliente");
		return "clientes/clientes-form.html";
	}
	
	@PostMapping("/guardar")
	public String guardarCliente(@ModelAttribute Cliente cliente) {
		clienteService.guardar(cliente);
		return "redirect:/cliente";
	}
	
	@PostMapping("/eliminar/{id}")
	public String eliminarCliente(@PathVariable Integer id, Model model){
		clienteService.deleteCliente(id);
		return "redirect:/cliente";
	}
}