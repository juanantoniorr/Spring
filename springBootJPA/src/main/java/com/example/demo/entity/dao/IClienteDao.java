package com.example.demo.entity.dao;

import java.util.List;

import com.example.demo.entity.Cliente;

public interface IClienteDao {
	//Lista que retorna lo que hay en la entidad Cliente
	public List<Cliente> findAll();
	public void save (Cliente cliente) ;
	public Cliente findOne (long id);
	public void eliminar (Long id);
	
	

}
