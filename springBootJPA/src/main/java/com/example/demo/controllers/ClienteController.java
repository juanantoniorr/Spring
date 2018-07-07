package com.example.demo.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Cliente;
//import org.springframework.web.bind.annotation.SessionAttributes;
import com.example.demo.entity.dao.IClienteDao;
import com.example.demo.service.IClienteService;
import com.example.demo.service.IUploadFileService;

@Controller
// Hace una sesion de cliente la cual debe cerrarse al momento de terminar de
// usarla
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	// Varibale que inicia la interfaz IClienteService
	private IClienteService clienteService;
	// Inyecto la interfaz IUploadFileService porque la declare como @Service
	@Autowired
	private IUploadFileService uploadFileService;
	
	
	
	@GetMapping (value = "/ver/{id}")
	//Con el @PathVariable especifico que puede cambiar el id en la url
	//El map es para mapear con llave valor con la vista, en este caso titulo en la vista le cambio la variable a Ver cliente
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteService.findOne(id);
			//model.put("titulo", "Ver");
			if (cliente == null) {
				flash.addFlashAttribute("error", "El cliente no existe");
				return "redirect:/listar";
			}

		} else {
			flash.addFlashAttribute("error", "El cliente no existe");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Ver cliente");
		return "ver";

	}
	

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
		Resource recurso = uploadFileService.load(filename);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);

	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
		Pageable pageRequest = new PageRequest(page, 5);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);

		model.addAttribute("titulo", "Listado de clientes");
		// Se crea el atributo clientes que se usa en el front
		// model.addAttribute("clientes", clienteService.findAll()); esto es sin
		// paginacion
		model.addAttribute("clientes", clientes);
		return "listar";
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("header", "Formulario del cliente");
		model.put("titulo", "Crear");

		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes flash, @RequestParam("file") MultipartFile foto) {// BindingResult verifica las
																					// validaciones de la clase entity

		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Formulario del cliente");
		String mensajeFlash = (cliente.getId() != null ? "Cliente editado con éxito" : "Cliente creado con éxito");
		if (result.hasErrors()) {
			return "form";

		}
		// Si la foto es diferente de empty o null
		if (!foto.isEmpty()) {

			// Path directorio = Paths.get("src/main/resources/static/upload/"); Solo se ve
			// dentro del proyecto pero no del server
			// String rootPath = "C://uploads";
			String uniqueFileName = uploadFileService.copy(foto);
			flash.addFlashAttribute("info", "Has subido correctamente ' " + uniqueFileName + "'");
			cliente.setFoto(uniqueFileName);
		}
		clienteService.save(cliente);
		// Se completa la sesion del cliente
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";

	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteService.findOne(id);
			model.put("titulo", "Editar");
			if (cliente == null) {
				flash.addFlashAttribute("error", "El cliente no existe");
				return "redirect:/listar";
			}

		} else {
			flash.addFlashAttribute("error", "El cliente no existe");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar cliente");
		return "form";

	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			Cliente cliente = clienteService.findOne(id);
			clienteService.eliminar(id);
			flash.addFlashAttribute("success", "Cliente eliminado");
			if (uploadFileService.delete(cliente.getFoto())) {
				flash.addFlashAttribute("info", "foto" + cliente.getFoto() + " eliminada con exito");

			}

		}
		return "redirect:/listar";

	}

}