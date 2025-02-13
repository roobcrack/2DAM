package com.fran.hibernateanotaciones2.entidades;

// default package
// Generated 10 dic 2024, 20:32:35 by Hibernate Tools 4.3.6.Final

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Libros generated by hbm2java
 */
@Entity
@Table(name = "libros")
public class Libros implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@Column(name = "titulo", length = 60)
	private String titulo;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "escribir", joinColumns = {
			@JoinColumn(name = "codlibro", referencedColumnName="id") }, inverseJoinColumns = {
					@JoinColumn(name = "codautor", referencedColumnName="cod") })
	private Set<Autores> autoreses = new HashSet<Autores>(0);

	public Libros() {
	}

	public Libros(int id) {
		this.id = id;
	}

	public Libros(int id, String titulo, Set<Autores> autoreses) {
		this.id = id;
		this.titulo = titulo;
		this.autoreses = autoreses;
	}

	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Set<Autores> getAutoreses() {
		return this.autoreses;
	}

	public void setAutoreses(Set<Autores> autoreses) {
		this.autoreses = autoreses;
	}

}
