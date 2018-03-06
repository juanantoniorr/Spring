package com.example.demo.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Cliente;
@Repository

public class ClienteDaoImpl implements IClienteDao {
	@PersistenceContext
	//Maneja las operaciones crud y otras cosas referentes con la entidad 
	private EntityManager em;
	@SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
	@Override
	public List<Cliente> findAll() {
	
		return em.createQuery("from Cliente").getResultList();
	}
	@Override
	@Transactional
	public void save(Cliente cliente) {
		if(cliente.getId() != null && cliente.getId() >0) {
			em.merge(cliente);
			
			
		} else {
			em.persist(cliente);
		}
		
		
		
	}
	@Override
	@Transactional(readOnly=true)
	public Cliente findOne(long id) {
		
		return em.find(Cliente.class, id);
	}
	@Override
	@Transactional
	public void eliminar(Long id) {
		Cliente cliente = findOne(id);
		em.remove(cliente);
		
	}

}
