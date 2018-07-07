package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//Clase que implementa la interfaz IUploadFileService
//Como componente service se puede inyectar
@Service
public class UploadFileServiceImpl implements IUploadFileService {
	// Con esto le digo que me muestre en el log lo que yo le pida
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final static String UPLOADS_FOLDER = "uploads";

	@Override
	public Resource load(String fileName) {
		Path pathFoto = getPath(fileName);
		log.info("path foto" + pathFoto);
		Resource recurso = null;
		try {
			recurso = new UrlResource(pathFoto.toUri());
			if (!recurso.exists() || !recurso.isReadable()) {
				throw new RuntimeException("no se puede cargar la imagen " + pathFoto.toString());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return recurso;
	}

	@Override
	public String copy(MultipartFile file) {
		// Path directorio = Paths.get("src/main/resources/static/upload/"); Solo se ve
		// dentro del proyecto pero no del server
		// String rootPath = "C://uploads";
		String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		Path rootPath = getPath(uniqueFileName);

		// Cocateno porque el metodo solo acepta string
		log.info("rootPath" + rootPath);
		try {
			// byte[] bytes = foto.getBytes();
			// Path rutaCompleta= Paths.get(rootPath + "/" + foto.getOriginalFilename());
			// Files.write(rutaCompleta, bytes);
			// flash.addFlashAttribute("info", "Has subido correctamente " +
			// foto.getOriginalFilename() );
			// cliente.setFoto(foto.getOriginalFilename());

			Files.copy(file.getInputStream(), rootPath);

		} catch (IOException e) {

			e.printStackTrace();
		}
		return uniqueFileName;
	}

	@Override
	public boolean delete(String filename) {
		Path rootPath = getPath(filename);
		File archivo = rootPath.toFile();
		if (archivo.exists() && archivo.canRead()) {
			
			if (archivo.delete()) {
				return true;
				
			};
			
		}
		return false;
	}

	// Pido el nombre del file y regreso la ruta con el nombre
	public Path getPath(String filename) {

		return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
	}

}
