package com.example.demo.entity;

import static org.mockito.Mockito.after;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
//al reves de como esta en la clase Factura, un cliente muchas facturas
@OneToMany (mappedBy=("cliente"),fetch = FetchType.LAZY, cascade=CascadeType.ALL) //cascade en caso de que se elimine un cliente se borra en cascada todas sus facturas
//MappedBy en la tabla facturas va a crear el id cliente gracias a MappedBy y solo se tiene que hacer en esta clase
//Se crea en automatico la llave foranea cliente en Factura con MappedBy porque es una relacion bidireccioanl
private List<Factura> facturas;
public String getFoto() {
	return foto;
}
//Se inicia el array de facturas
public Cliente() {
	facturas = new ArrayList<Factura>();
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
public List<Factura> getFacturas() {
	return facturas;
}
//Agrega la lista de facturas
public void setFacturas(List<Factura> facturas) {
	this.facturas = facturas;
}
//Agrega las facturas una por una 
public void addFactura(Factura factura) {
	facturas.add(factura);
	
	
}

}
