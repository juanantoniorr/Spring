package com.example.demo.service;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

//En esta interfaz voy a poner los metodos necesarios para la carga de archivos
public interface IUploadFileService {
public Resource load (String fileName);
//Multipart es para hacer una peticion por http de un file para guardar en servidor
public String copy (MultipartFile file);
//Para saber si lo borro o no 
public boolean delete (String filename);
//borra todo el directorio uploads
public void deleteAll();
//Crea el directorio uploads
public void init() throws IOException;
}
