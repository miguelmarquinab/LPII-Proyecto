package com.lp2.sisinventario.sistemainventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lp2.sisinventario.sistemainventario.model.Cliente;
import com.lp2.sisinventario.sistemainventario.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public String getClientes(Model model){
		model.addAttribute("clientes", clienteService.getClientes());
		return "clientes.html";
	}
	
	@GetMapping("/nuevo")
	public String nuevoCliente(Model model){
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("titulo", "Nuevo cliente");
		return "clientes-form.html";
	}
}
	
