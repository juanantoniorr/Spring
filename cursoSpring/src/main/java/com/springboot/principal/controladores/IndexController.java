package com.springboot.principal.controladores;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@Value("${controller.bienvenida}")
	private String bienvenida;

	
	
	@GetMapping ("/index")
	public String index (Model model){
		model.addAttribute("titulo", bienvenida);
		return "index";
		
	}

}
