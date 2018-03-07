package com.example.demo.entity.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Cliente;
//La interfaz crud repository puede trabajar con cualquier entidad, en este caso cliente
public interface IClienteDao extends CrudRepository<Cliente, Long> {
	
	
	
	

}
