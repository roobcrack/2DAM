package com.ruben.xmljson.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.ruben.xmljson.entities.People;

public class JsonUtils {
	
	/**
	 * Example with with json-simple with a file
	 * 
	 * @param path Absolute path of the json file
	 */
	public static void leerJsonDesdeFichero(String path) {
		Object obj;
		try {
			// parseado el fichero "profesor.json"
			obj = new JSONParser().parse(new FileReader(path));
			// casteando obj a JSONObject
			JSONObject jo = (JSONObject) obj;
			
			//Picks the data and saves it
			String name = (String) jo.get("name");
			String height = (String) jo.get("height");
			JSONArray movies = (JSONArray) jo.get("films");
			
			//Prints all the saved
			//To print single value
			System.out.println(name);
			System.out.println(height);
			//To print an array
			movies.stream().forEach(e->System.out.println(e));
			//To print a list
			//Map list = ((Map) jo.get("list"));
			//domicilio.forEach((k,v) -> System.out.println("Key: " + k + ": Value: " + v));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Reads the json file from an url
	 * 
	 * @param url The url to the json
	 */
	public static void leerJsonDesdeUrl(String url) {
		Object obj;
		try {
			// parseado el fichero "profesor.json"
			obj = new JSONParser().parse(InternetUtils.readUrl(url));
			// casteando obj a JSONObject
			JSONObject jo = (JSONObject) obj;
			
			//Picks the data and saves it
			String name = (String) jo.get("name");
			String height = (String) jo.get("height");
			JSONArray movies = (JSONArray) jo.get("films");
			
			//Prints all the saved
			//To print single value
			System.out.println(name);
			System.out.println(height);
			//To print an array
			movies.stream().forEach(e->System.out.println(e));
			//To print a list
			//Map list = ((Map) jo.get("list"));
			//domicilio.forEach((k,v) -> System.out.println("Key: " + k + ": Value: " + v));
			
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static People leerStarWars(String url) {
		Gson gson = new Gson();
		People p = gson.fromJson(InternetUtils.readUrl(url), People.class);
		return p;
	}
	
	
	
	
	public static <T> T leerGenerico(String url, Class<T> clase) {
		return new Gson().fromJson(InternetUtils.readUrl(url), clase);
	}
}
