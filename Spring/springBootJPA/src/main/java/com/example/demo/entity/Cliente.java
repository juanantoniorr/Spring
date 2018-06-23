package com.example.demo.entity;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="clientes")

public class Cliente implements Serializable {
private static final long serialVersionUID = 1L;
@Id
//Hace autoincrementable 
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@NotEmpty
private String nombre;
@NotEmpty
private String apellido;
@NotEmpty
@Email
private String email;
//@Temporal (TemporalType.DATE)
@DateTimeFormat(pattern="yyyy-MM-dd") //Asi puedo especificar mi formato de fecha
@NotNull
private String creacion;
//Invoca al metodo antes de que se escriba todo en la base de datos 
//@PrePersist
//public void prePersist() {
//	creacion = new Date();
//}
private String foto;
public String getFoto() {
	return foto;
}
public void setFoto(String foto) {
	this.foto = foto;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getApellido() {
	return apellido;
}
public void setApellido(String apellido) {
	this.apellido = apellido;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getCreacion() {
	return creacion;
}
public void setCreacion(String creacion) {
	this.creacion = creacion;
}


}
