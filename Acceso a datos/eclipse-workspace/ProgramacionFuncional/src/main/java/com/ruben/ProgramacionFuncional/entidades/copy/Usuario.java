package com.ruben.ProgramacionFuncional.entidades.copy;

public class Usuario {
	int id;
	String nombre;
	double sueldo;
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Usuario(int id, String nombre, double sueldo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.sueldo = sueldo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getSueldo() {
		return sueldo;
	}
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", sueldo=" + sueldo + "]";
	}
	
	
}
