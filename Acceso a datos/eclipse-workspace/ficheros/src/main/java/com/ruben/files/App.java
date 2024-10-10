package com.ruben.files;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ruben.files.entidades.Alumno;
import com.ruben.files.utils.FilesUtils;
import com.ruben.files.utils.SerializacionUtils;

/**
 * Hello world!
 */
public class App {
	
	public static void pruebasFicheros() {
		//FicherosUtils.leerFichero("C:\\ficheros\\dam2425.txt")
    	//lineas de mas de 4 letras
    	////FicherosUtils.devolverLineasFichero("C:\\ficheros","dam2425.txt").stream().filter(e->e.length()>4).forEach(e->System.out.println(e));
    	//lineas que contienen una a
    	////FicherosUtils.devolverLineasFichero("C:\\ficheros","dam2425.txt").stream().filter(e->e.contains("a")).forEach(e->System.out.println(e));
    	
    	/*List<Alumno> alumnos = new ArrayList<Alumno>();
    	FicherosUtils.devolverLineasFichero("C:\\ficheros", "dam2425.txt").stream()
    		.filter(e->e.contains("2DAM"))
    		.forEach(alumno->alumnos.add( new Alumno(
    						alumno.split(" ")[0],
    						alumno.split(" ")[1],
    						Double.parseDouble(alumno.split(" ")[2].replace(',','.')))));
    						
    	alumnos.forEach(e->System.out.println(e));*/
  	
    	List<String> lista = new ArrayList<String>();
    	lista.add("DAM joselu 4.3");
    	lista.add("DAW perico 3.3");
    	if(FilesUtils.writeFile("C:/ficheros/prueba2425.txt", lista))
    		System.out.println("El fichero se ha reescrito correctamente");


    	if(FilesUtils.addFile("C:/ficheros/prueeeasdba2425.txt", lista))
    		System.out.println("Fichero añadido correctamente");
    	else
    		System.out.println("OJOLIN");
	}
    public static void pruebasSerializacion() {
    	/*if(SerializacionUtils.serializarObjeto("C:/ficheros/alumnos24.dat",
    			new Alumno("2DAM firulais 10.0"))
    		System.out.println("Serializacion correcta");*/
    		
    }
    
    
    
    
	public static void main(String[] args){
    	pruebasSerializacion();
    }
}