package com.example.demo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.entity.Cliente;
//import org.springframework.web.bind.annotation.SessionAttributes;
import com.example.demo.entity.dao.IClienteDao;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	//Varibale que inicia la interfaz IClienteDao para que clienteService pueda usar sus metodos
	private IClienteDao clienteService;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteService.findAll());
		return "listar";
	}
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String crear(Map<String,Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "formulario del cliente");
		return "form";
	}
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar (Cliente cliente) {
		clienteService.save(cliente);
		return "redirect:listar";
		
		
		
	}
	
}