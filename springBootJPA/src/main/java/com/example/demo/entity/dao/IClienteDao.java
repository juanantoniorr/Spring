package com.example.demo.entity.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.entity.Cliente;
//La interfaz crud repository puede trabajar con cualquier entidad, en este caso cliente
//public interface IClienteDao extends CrudRepository<Cliente, Long> Esta interfaz tiene metodos crud
//Esta interfaz tambien hereda de crudRepository tiene esos metodos mas los de paginacion
public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>
{
	
	
	
	

}
