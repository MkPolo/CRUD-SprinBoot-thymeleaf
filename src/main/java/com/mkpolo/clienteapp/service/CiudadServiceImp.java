package com.mkpolo.clienteapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkpolo.clienteapp.entity.Ciudad;
import com.mkpolo.clienteapp.repository.CiudadRepository;

@Service
public class CiudadServiceImp implements ICiudadService {

	@Autowired
	private CiudadRepository ciudadRepository;
	
	@Override
	public List<Ciudad> listarCiudad() {
		
		return (List<Ciudad>) ciudadRepository.findAll();
	}

}
