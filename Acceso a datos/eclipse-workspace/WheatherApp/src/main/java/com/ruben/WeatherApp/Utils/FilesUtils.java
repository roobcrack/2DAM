package com.ruben.WeatherApp.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FilesUtils {
	
	public static List<String> returnFileListed(String path){
		try {
			return Files.readAllLines(Paths.get(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
