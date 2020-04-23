package com.mkpolo.clienteapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mkpolo.clienteapp.entity.Cliente;
import com.mkpolo.clienteapp.service.IClienteService;

@Controller
@RequestMapping("/views/clientes")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/")
	public String listarClientes(Model model) {
		List<Cliente> listadoClientes = clienteService.listartodos();
		
		model.addAttribute("titulo","Lista de Clientes");
		model.addAttribute("clientes", listadoClientes);
		
		return "/views/clientes/listar";
	}
	
}
