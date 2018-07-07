package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.service.IUploadFileService;

@SpringBootApplication
public class SpringBootJpaApplication implements CommandLineRunner { //interfaz que actua en cuanto se ejecuta la app
	//Inyecto la interfaz para crear y eliminar el directorio
	@Autowired
	IUploadFileService uploadFileService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaApplication.class, args);
	}
	//Es bueno utilizar esta anotacion para hacer el codigo mas legible y que te avise si es que estas usando mal
	//el metodo 
	@Override
	public void run(String... arg0) throws Exception {
		//Elimina el directorio uploads si existe y despues lo crea todo al iniciar la app
		uploadFileService.deleteAll();
		uploadFileService.init();
		
		
	}
}
