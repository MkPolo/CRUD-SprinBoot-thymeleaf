package com.mkpolo.clienteapp.service;

import java.util.List;

import com.mkpolo.clienteapp.entity.Cliente;

public interface IClienteService {

	public List<Cliente> listartodos();
	
	public void guardar(Cliente cliente);
	
	public Cliente buscarPorId(Long id);
	
	public void eliminar(Long id);
	
}
