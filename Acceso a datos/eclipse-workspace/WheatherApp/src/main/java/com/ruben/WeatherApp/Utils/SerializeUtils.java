package com.ruben.WeatherApp.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtils {
	
	public static <T> boolean serializeObject(String rutaCompleta, T objeto) {
		try {
			File file = new File(rutaCompleta);
			FileOutputStream outFile = new FileOutputStream(file);
			ObjectOutputStream fileObject = new ObjectOutputStream(outFile);
			fileObject.writeObject(objeto);
			fileObject.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static <T> T deserializeObject(String rutaCompleta) {
		try {
			File file = new File(rutaCompleta);
			FileInputStream outFile = new FileInputStream(file);
			ObjectInputStream fileObject = new ObjectInputStream(outFile);
			@SuppressWarnings("unchecked")
			T object = (T)fileObject.readObject();
			fileObject.close();
			return object;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
