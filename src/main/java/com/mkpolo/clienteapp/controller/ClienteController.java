package com.mkpolo.clienteapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mkpolo.clienteapp.entity.Ciudad;
import com.mkpolo.clienteapp.entity.Cliente;
import com.mkpolo.clienteapp.service.ICiudadService;
import com.mkpolo.clienteapp.service.IClienteService;

@Controller
@RequestMapping("/views/clientes")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private ICiudadService ciudadService;

	@GetMapping("/")
	public String listarClientes(Model model) {
		List<Cliente> listadoClientes = clienteService.listartodos();

		model.addAttribute("titulo", "Lista de Clientes");
		model.addAttribute("clientes", listadoClientes);

		return "/views/clientes/listar";
	}

	@GetMapping("/create")
	public String crear(Model model) {

		Cliente cliente = new Cliente();
		List<Ciudad> listCiudades = ciudadService.listarCiudad();

		model.addAttribute("titulo", "Formulario: Nuevo Cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("ciudades", listCiudades);

		return "/views/clientes/frmCrear";
	}

	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Cliente cliente, BindingResult result,
			Model model, RedirectAttributes attribute) {

		List<Ciudad> listCiudades = ciudadService.listarCiudad();

		if (result.hasErrors()) {

			model.addAttribute("titulo", "Formulario: Nuevo Cliente");
			model.addAttribute("cliente", cliente);
			model.addAttribute("ciudades", listCiudades);

			return "/views/clientes/frmCrear";
		}

		clienteService.guardar(cliente);
		attribute.addFlashAttribute("success","Cliente guardado con exito!");
		return "redirect:/views/clientes/";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Long idCliente,
			Model model, RedirectAttributes attribute) {

		Cliente cliente = null;

		if (idCliente > 0) {
			cliente = clienteService.buscarPorId(idCliente);

			if (cliente == null) {
				attribute.addFlashAttribute("error", "ATENCIÓN: El ID del cliente no existe!");
				return "redirect:/views/clientes/";
			}
		} else {
			attribute.addFlashAttribute("error", "ATENCIÓN: Error con el ID del cliente!");
			return "redirect:/views/clientes/";
		}

		List<Ciudad> listCiudades = ciudadService.listarCiudad();		
		model.addAttribute("titulo", "Formulario: Editar Cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("ciudades", listCiudades);

		return "/views/clientes/frmCrear";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Long idCliente, RedirectAttributes attribute) {

		Cliente cliente = null;

		if (idCliente > 0) {
			cliente = clienteService.buscarPorId(idCliente);

			if (cliente == null) {
				attribute.addFlashAttribute("error", "ATENCIÓN: El ID del cliente no existe!");
				return "redirect:/views/clientes/";
			}
		} else {
			attribute.addFlashAttribute("error", "ATENCIÓN: Error con el ID del cliente!");
			return "redirect:/views/clientes/";
		}

		clienteService.eliminar(idCliente);
		attribute.addFlashAttribute("warning", "Registro eliminado con exito!");
		return "redirect:/views/clientes/";
	}

}
