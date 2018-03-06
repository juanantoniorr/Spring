package com.example.demo.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.dao.ClienteDaoImpl;
//import org.springframework.web.bind.annotation.SessionAttributes;
import com.example.demo.entity.dao.IClienteDao;

@Controller
//Hace una sesion de cliente la cual debe cerrarse al momento de terminar de usarla 
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	//Varibale que inicia la interfaz IClienteDao para que clienteService pueda usar sus metodos
	private IClienteDao clienteService;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		//Se crea el atributo clientes que se usa en el front
		model.addAttribute("clientes", clienteService.findAll());
		return "listar";
	}
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String crear(Map<String,Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario del cliente");
		return "form";
	}
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar (@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {//BindingResult verifica las validaciones de la clase entity
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Formulario del cliente");
		if(result.hasErrors()) {
			return "form";
			
		}
		clienteService.save(cliente);
		//Se completa la sesion del cliente
		status.setComplete();
		return "redirect:listar";
		
		
		
	}
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		Cliente cliente = null;
		if(id>0) {
			cliente = clienteService.findOne(id);
			
			
		} else {
			return "redirect:listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar cliente");
		return "form" ;
		
		
		
	}
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		if(id>0) {
			clienteService.eliminar(id);
			
			
		} 
		return "redirect:/listar";
		
		
	}
	
	
	
	
}