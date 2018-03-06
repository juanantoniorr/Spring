package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Cliente;

public interface IClienteService {
	public List<Cliente> findAll();
	public void save (Cliente cliente) ;
	public Cliente findOne (long id);
	public void eliminar (Long id);
	
}
