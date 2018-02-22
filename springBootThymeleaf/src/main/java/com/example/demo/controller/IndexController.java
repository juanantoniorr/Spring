package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller


public class IndexController {
	@Value("${controller.mensaje}")
	private String mensaje;
	@GetMapping("/")
	//Este metodo puede llevar el nombre que sea 
	public String hol(Model model ) {
		model.addAttribute("mensaje", mensaje);
		return "hola"; //el nombre de la vista 
		
		
		
	}
	
	
	

}
