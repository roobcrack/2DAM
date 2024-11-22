package com.edu.ruben.JokesApp.Controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.ruben.JokesApp.Entities.InfoResponse;
import com.edu.ruben.JokesApp.Entities.Joke;
import com.edu.ruben.JokesApp.Entities.JokeRequest;
import com.edu.ruben.JokesApp.Utils.JdbcUtils;
import com.edu.ruben.JokesApp.Utils.JsonUtils;

@RestController
@RequestMapping("/api")
public class JokesController {
	
	@GetMapping("/reset")
	public static String cargarInfo() {
	    System.out.println("Connecting");
	    try {
	        JdbcUtils.conexionBbdd("jdbc:postgresql://localhost:5432/jokes", "postgres", "postgres");
	        System.out.println("Connected");
	        
	        // Truncate all existing data
	        JdbcUtils.ejecutarDML(""
	            + "TRUNCATE TABLE categories CASCADE;"
	            + "TRUNCATE TABLE flags CASCADE;"
	            + "TRUNCATE TABLE jokes CASCADE;"
	            + "TRUNCATE TABLE jokes_flags CASCADE;"
	            + "TRUNCATE TABLE languages CASCADE;"
	            + "TRUNCATE TABLE types CASCADE;");
	        System.out.println("Truncated");
	        
	        // Fetch information from the API
	        InfoResponse infoResponse = JsonUtils.readGeneric("https://v2.jokeapi.dev/info", InfoResponse.class);

	        if (infoResponse != null && !infoResponse.isError()) {
	            // Insert categories
	            infoResponse.getJokes().getCategories().stream()
	                .forEach(category -> JdbcUtils.ejecutarDML("INSERT INTO categories (name) VALUES ('" + category + "')"));

	            // Insert flags
	            infoResponse.getJokes().getFlags().stream()
	                .forEach(flag -> JdbcUtils.ejecutarDML("INSERT INTO flags (name) VALUES ('" + flag + "')"));

	            // Insert types
	            infoResponse.getJokes().getTypes().stream()
	                .forEach(type -> JdbcUtils.ejecutarDML("INSERT INTO types (name) VALUES ('" + type + "')"));

	            // Insert languages
	            infoResponse.getJokes().getSafeJokes().stream()
	                .forEach(lang -> JdbcUtils.ejecutarDML("INSERT INTO languages (code, joke_count) VALUES ('" + lang.getLang() + "', " + lang.getCount() + ")"));
	            System.out.println("Inserting jokes");
	            
	            // Fetch jokes using totalCount
	            for (int i = 0; i < 10; i++) {
	                // Fetch each joke by ID
	                String url = "https://v2.jokeapi.dev/joke/Any?idRange=" + i + "," + i;
	                Joke joke = JsonUtils.readGeneric(url, Joke.class);

	                if (joke != null) {
	                    // Determine how to handle null values
	                    String setup = (joke.getSetup() == null) ? "NULL" : "'" + joke.getSetup().replace("'", "''") + "'";
	                    String delivery = (joke.getDelivery() == null) ? "NULL" : "'" + joke.getDelivery().replace("'", "''") + "'";
	                    String jokeText = (joke.getJoke() == null) ? "NULL" : "'" + joke.getJoke().replace("'", "''") + "'";

	                    // Insert the joke into the database with a manually specified 'id'
	                    String jokeInsertQuery = "INSERT INTO jokes (id, id_language, category_name, type_name, setup, delivery, joke) "
	                        + "VALUES (" + joke.getId() + ", '" + joke.getLang() + "', '" + joke.getCategory() + "', '" + joke.getType() + "', "
	                        + setup + ", " + delivery + ", " + jokeText + ")";
	                    JdbcUtils.ejecutarDML(jokeInsertQuery);

	                    // Insert flags for the joke
	                    insertJokeFlags(joke.getId(), joke.getLang(), joke.getFlags());
	                }
	            }
	        } else {
	            return ("Error: Could not retrieve information or API error occurred.");
	        }
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    } finally { 
	        JdbcUtils.cerrarBbdd(); 
	    }
	    JdbcUtils.cerrarBbdd();
	    System.out.println("Ended");
	    return ("Ended");
	}

	private static void insertJokeFlags(int jokeId, String languageCode, Joke.Flags flags) {
	    if (flags != null) {
	        if (flags.isNsfw()) {
	            JdbcUtils.ejecutarDML("INSERT INTO jokes_flags (id_joke, id_language, flag_name) VALUES (" + jokeId + ", '" + languageCode + "', 'nsfw')");
	        }
	        if (flags.isReligious()) {
	            JdbcUtils.ejecutarDML("INSERT INTO jokes_flags (id_joke, id_language, flag_name) VALUES (" + jokeId + ", '" + languageCode + "', 'religious')");
	        }
	        if (flags.isPolitical()) {
	            JdbcUtils.ejecutarDML("INSERT INTO jokes_flags (id_joke, id_language, flag_name) VALUES (" + jokeId + ", '" + languageCode + "', 'political')");
	        }
	        if (flags.isRacist()) {
	            JdbcUtils.ejecutarDML("INSERT INTO jokes_flags (id_joke, id_language, flag_name) VALUES (" + jokeId + ", '" + languageCode + "', 'racist')");
	        }
	        if (flags.isSexist()) {
	            JdbcUtils.ejecutarDML("INSERT INTO jokes_flags (id_joke, id_language, flag_name) VALUES (" + jokeId + ", '" + languageCode + "', 'sexist')");
	        }
	        if (flags.isExplicit()) {
	            JdbcUtils.ejecutarDML("INSERT INTO jokes_flags (id_joke, id_language, flag_name) VALUES (" + jokeId + ", '" + languageCode + "', 'explicit')");
	        }
	    }
	}

	@PostMapping("/add-joke")
	public String addJoke(@RequestBody JokeRequest jokeRequest) {
	    try {
	        // Step 1: Establish database connection
	        JdbcUtils.conexionBbdd("jdbc:postgresql://localhost:5432/jokes", "postgres", "postgres");

	        // Step 2: Check the type of joke (single or two-part) and prepare the values
	        String setup = null;
	        String delivery = null;
	        String jokeText = null;
	        
	        if ("single".equalsIgnoreCase(jokeRequest.getType())) {
	            // Type 1: Single joke
	            jokeText = jokeRequest.getJoke(); // Only joke text is provided
	        } else if ("twopart".equalsIgnoreCase(jokeRequest.getType())) {
	            // Type 2: Two-part joke
	            setup = jokeRequest.getSetup();  // First part (setup)
	            delivery = jokeRequest.getDelivery();  // Second part (delivery)
	        } else {
	            return "Error: Invalid joke type. Valid types are 'single' or 'twopart'.";
	        }
	        
	        // Step 3: Prepare the SQL query for inserting the joke into the 'jokes' table
	        String jokeInsertQuery = "INSERT INTO jokes (id, id_language, category_name, type_name, setup, delivery, joke) "
	                + "VALUES (" + jokeRequest.getId() + ", '" + jokeRequest.getLanguage() + "', '" 
	                + jokeRequest.getCategory() + "', '" + jokeRequest.getType() + "', "
	                + (setup != null ? "'" + setup.replace("'", "''") + "'" : "NULL") + ", "
	                + (delivery != null ? "'" + delivery.replace("'", "''") + "'" : "NULL") + ", "
	                + (jokeText != null ? "'" + jokeText.replace("'", "''") + "'" : "NULL") + ")";

	        // Step 4: Execute the joke insertion
	        int rowsInserted = JdbcUtils.ejecutarDML(jokeInsertQuery);
	        if (rowsInserted > 0) {
	            // Step 5: Insert the flags for this joke
	            insertJokeFlags(jokeRequest.getId(), jokeRequest.getLanguage(), jokeRequest.getFlags());
	            return "Joke added successfully!";
	        } else {
	            return "Error: Failed to add joke to the database.";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Error: An unexpected error occurred. " + e.getMessage();
	    } finally {
	        JdbcUtils.cerrarBbdd();
	    }
	}
	
	private static void insertJokeFlags(int jokeId, String languageCode, Map<String, Boolean> flags) {
	    if (flags != null) {
	        if (flags.getOrDefault("nsfw", false)) {
	            JdbcUtils.ejecutarDML("INSERT INTO jokes_flags (id_joke, id_language, flag_name) VALUES (" + jokeId + ", '" + languageCode + "', 'nsfw')");
	        }
	        if (flags.getOrDefault("religious", false)) {
	            JdbcUtils.ejecutarDML("INSERT INTO jokes_flags (id_joke, id_language, flag_name) VALUES (" + jokeId + ", '" + languageCode + "', 'religious')");
	        }
	        if (flags.getOrDefault("political", false)) {
	            JdbcUtils.ejecutarDML("INSERT INTO jokes_flags (id_joke, id_language, flag_name) VALUES (" + jokeId + ", '" + languageCode + "', 'political')");
	        }
	        if (flags.getOrDefault("racist", false)) {
	            JdbcUtils.ejecutarDML("INSERT INTO jokes_flags (id_joke, id_language, flag_name) VALUES (" + jokeId + ", '" + languageCode + "', 'racist')");
	        }
	        if (flags.getOrDefault("sexist", false)) {
	            JdbcUtils.ejecutarDML("INSERT INTO jokes_flags (id_joke, id_language, flag_name) VALUES (" + jokeId + ", '" + languageCode + "', 'sexist')");
	        }
	        if (flags.getOrDefault("explicit", false)) {
	            JdbcUtils.ejecutarDML("INSERT INTO jokes_flags (id_joke, id_language, flag_name) VALUES (" + jokeId + ", '" + languageCode + "', 'explicit')");
	        }
	    }
	}

}
