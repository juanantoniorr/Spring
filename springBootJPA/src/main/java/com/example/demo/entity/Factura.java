package com.example.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
//La anotacion table se puede omitir si se llama igual a la tabla la clase pero es buena practica tabla en plural y clase en singular
@Table(name="facturas")

public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;
	public Factura() {
		this.items = new ArrayList<ItemFactura>(); //inicializo la variable items 
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descripcion;
	private String observaciones;
	@Temporal (TemporalType.DATE)
	@Column (name= "create_at") //la columna de una tabla se separa con _ y el atributo con convencion java
	private Date createAt;
	@ManyToOne (fetch=FetchType.LAZY, cascade= CascadeType.ALL) //fetch lazy y eager en el momento de una consulta lazy te trae datos solo de una tabla y eager de las 2 relaciones 
	//Muchos (la clase en la que estoy) a uno (el objeto que esta abajo)
	//Un cliente puede tener muchas facturas
	private Cliente cliente;
	//Una factura puede tener muchos items pero un item solo puede tener una factura
	@OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//igual se puede omitir y por defecto se hace automaticamente pero solo si la tabla se llama igual a la clase 
	//Es muy importante para que genere la llave foranea en ItemFactura ya que la relacion es unidireccional
	@JoinColumn(name = "factura_id") //Asi tenemos la llave foranea factura_id en esta tabla "Factura", el nombre puede ser cualquiera
	private List <ItemFactura> items;
	public List<ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}

	@PrePersist
	public void prePersist() {
		createAt = new Date(); //crea en automatico la fecha
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public void addItemFactura (ItemFactura item) {
		this.items.add(item);
		
	}

}
