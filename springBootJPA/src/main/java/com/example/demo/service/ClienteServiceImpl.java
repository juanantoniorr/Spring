package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.dao.IClienteDao;

//Con esta clase puedo usar diferentes dao
@Service
public class ClienteServiceImpl implements IClienteService {
	@Autowired
	IClienteDao clienteDao;
	@Transactional(readOnly=true)
	@Override
	public List<Cliente> findAll() {
		
		return (List<Cliente>) clienteDao.findAll();
	}

	@Transactional
	@Override
	public void save(Cliente cliente) {
	
		clienteDao.save(cliente);
	}

	@Transactional(readOnly=true)
	@Override
	public Cliente findOne(long id) {
		
		return clienteDao.findOne(id);
	}

	@Transactional
	@Override
	public void eliminar(Long id) {
		clienteDao.delete(id);
		
	}
	

}
