package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="clientes")

public class Cliente implements Serializable {
private static final long serialVersionUID = 1L;
@Id
//Hace autoincrementable 
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String nombre;
private String apellido;
private String email;
@Temporal (TemporalType.DATE)
private Date creacion;
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
public Date getCreacion() {
	return creacion;
}
public void setCreacion(Date creacion) {
	this.creacion = creacion;
}


}
