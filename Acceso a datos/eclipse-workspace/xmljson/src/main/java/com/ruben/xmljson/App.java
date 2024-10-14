package com.ruben.xmljson;

import com.ruben.xmljson.utils.JsonUtils;

import java.util.ArrayList;

import com.ruben.xmljson.entities.People;
import com.ruben.xmljson.utils.InternetUtils;
import com.ruben.xmljson.utils.XmlUtils;

public class App {
	
	public static void pruebasXml() {
		//XmlUtils.procesarXmlSax();  // Solamente usar para archivos extremadamente grandes
    	//XmlUtils.procesarAsignatura();
    	/*XmlUtils.procesarMarca("C:/ficheros/portada.xml").stream()
    	.filter(e->e.getTitulo().contains("Madrid"))
    	.forEach(e->System.out.println(e));*/
		//String xml = InternetUtils.readUrl("https://e00-marca.uecdn.es/rss/portada.xml");
		
	}
	
	public static void pruebasJson() {
		//JsonUtils.leerJsonDesdeFichero("C:/ficheros/profesor.json");
		//JsonUtils.leerLuke("C:/ficheros/luke.json");
		// filtro sobre la marcha
		
		JsonUtils.devolverTareasInternet("https://jsonplaceholder.typicode.com/todos").stream()
			.filter(e->e.isCompleted()==true)
			.forEach(e->System.out.println(e));
		List<Tareas> tareas = JsonUtils.devolverTareasInternet("https://jsonplaceholder.typicode.com/todos");
		
		/*
		System.out.println(
		JsonUtils.leerStarWars("https://swapi.dev/api/people/1/?format=json")
				);
		*/
		//System.out.println(JsonUtils.leerGenerico("https://swapi.dev/api/people/1/?format=json", People.class));
		//System.out.println(JsonUtils.leerGenerico("https://swapi.dev/api/films/1/?format=json", Films.class));
		
		// Coger todos los personajes
		List<People> personajes = new ArrayList<People>();
		for (int i = 1; i<=4; i++) {
			personajes.add(
			JsonUtils.leerGenerico("https://swapi.dev/api/people/" + i + "/?format=json", People.class)
			);
		}
		
		personajes.forEach(e->System.out.println(e));
		System.out.println(personajes.size());
		
	}
}