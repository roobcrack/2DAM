package com.ruben.files.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FilesUtils {
	
	/**
	 * Given a file it prints it in console <FOR TESTING>
	 * @param path Absolute path of the file
	 */
	public static void readFile(String path){
		try {
			Files.readAllLines(Paths.get(path)).forEach(e->System.out.println(e));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//////////////////////////																	RETURN FILE AS A LIST
	/**
	 * Returns the file content in a list
	 * @param directory Path where the folder is
	 * @param fileName Name of the file to read
	 * @param charset Charset to use
	 * @return Lines of the file listed
	 */
	public static List<String> returnFileListed(String directory, String fileName, Charset charset){
		try {
			return Files.readAllLines(Paths.get(directory+File.separator+fileName+charset));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	/**
	 * Returns the file content in a list using default Charset
	 * @param directory Path where the folder is
	 * @param fileName Name of the file to read
	 * @return Lines of the file listed
	 */
	public static List<String> returnFileListed(String directory, String nameFile){
		return returnFileListed(directory, nameFile, Charset.defaultCharset());
	}
	
	/**
	 * Returns the file content in a list using default Charset
	 * @param path Absolute path of the file
	 * @return Lines of the file listed
	 */
	public static List<String> returnFileListed(String path){
		File file = new File(path);
		return returnFileListed(file.getParent(), file.getName());
	}
	
	////////////////////																			RETURN FILES AS STRING
	/**
	 * Given a file, set its lines in a single string
	 * @param directory Path where the folder is
	 * @param fileName Name of the file to read
	 * @param charset Charset to use
	 * @return File content in a string
	 */
	public static String returnFile(String directory, String nameFile, Charset charset) {
		try {
			return Files.readString(Paths.get(directory+File.separator+nameFile+charset));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Given a file, set its lines in a single string with default charset
	 * @param directory Path where the folder is
	 * @param fileName Name of the file to read
	 * @return File content in a string
	 */
	public static String returnFile(String directory, String nameFile) {
		return returnFile(directory, nameFile, Charset.defaultCharset());
	}
	
	/**
	 * Given a file, set its lines in a single string with default charset
	 * @param path Absolute path of the file
	 * @return File content in a string
	 */
	public static String returnFile(String path) {
		File file = new File(path);
		return returnFile(file.getParent(), file.getName());
	}
	
	
	
	//																					WRITE FILES
	/**
	 * Given a path and a list of lines creates a file in that path. 
	 * [[IF THE FILE EXISTS IT REWRITES IT]]
	 * @param path Absolute path of the file
	 * @param lines Lines as a list to write 
	 * @return TRUE if it could create or rewrite
	 */
	public static boolean writeFile(String path, List<String> lines) {
		try {
			Files.write(Paths.get(path), lines);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Given a path and a list of lines adds it to a file or creates it. 
	 * [[IF DOES NOT EXIST IT CREATES IT]]
	 * @param path Absolute path of the file
	 * @param lines Lines as a list to write 
	 * @return TRUE if it could be added or created
	 */
	public static boolean addFile(String path, List<String> lines) {
		try {
			if(Files.exists(Paths.get(path)))
				Files.write(Paths.get(path), lines, StandardOpenOption.APPEND);
			else
				Files.write(Paths.get(path), lines, StandardOpenOption.CREATE);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
