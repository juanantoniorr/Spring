package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Cliente;

public interface IClienteService {
	public List<Cliente> findAll();
	public Page<Cliente> findAll(Pageable pageable);
	
	public void save (Cliente cliente) ;
	public Cliente findOne (long id);
	public void eliminar (Long id);
	
}
