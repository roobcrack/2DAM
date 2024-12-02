package com.edu.ruben.JokesApp.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.ruben.JokesApp.Entities.InfoResponse;
import com.edu.ruben.JokesApp.Entities.Joke;
import com.edu.ruben.JokesApp.Entities.Joke.Flags;
import com.edu.ruben.JokesApp.Entities.JokeRequest;
import com.edu.ruben.JokesApp.Utils.JdbcUtils;
import com.edu.ruben.JokesApp.Utils.JsonUtils;

@RestController
@RequestMapping("/api")
public class JokesController {
    public static String db = "jdbc:postgresql://localhost:5432/jokes";
    public static String name = "postgres";
    public static String password = "postgres";

    /**
     * Resets the database by truncating all tables and reloading joke-related information 
     * from the Joke API. The method fetches categories, flags, types, and jokes based on 
     * language ranges from the API and populates the database accordingly.
     *
     * @return A message indicating the result of the reset operation.
     */
    @GetMapping("/reset")
    public static String cargarInfo() {
        System.out.println("Connecting");
        try {
            JdbcUtils.conexionBbdd(db, name, password);
            System.out.println("Connected");

            JdbcUtils.ejecutarDML("TRUNCATE TABLE categories CASCADE;" +
                                  "TRUNCATE TABLE flags CASCADE;" +
                                  "TRUNCATE TABLE jokes CASCADE;" +
                                  "TRUNCATE TABLE jokes_flags CASCADE;" +
                                  "TRUNCATE TABLE types CASCADE;" +
                                  "TRUNCATE TABLE language_ranges CASCADE;");
            System.out.println("Truncated");
            InfoResponse infoResponse = JsonUtils.readGeneric("https://v2.jokeapi.dev/info", InfoResponse.class);

            if (infoResponse != null && !infoResponse.isError()) {
                infoResponse.getJokes().getCategories().forEach(
                        category -> JdbcUtils.ejecutarDML("INSERT INTO categories (name) VALUES ('" + category + "')"));
                infoResponse.getJokes().getFlags().forEach(
                        flag -> JdbcUtils.ejecutarDML("INSERT INTO flags (name) VALUES ('" + flag + "')"));
                infoResponse.getJokes().getTypes().forEach(
                        type -> JdbcUtils.ejecutarDML("INSERT INTO types (name) VALUES ('" + type + "')"));
                
                System.out.println("Inserting language ranges");
                Map<String, int[]> idRanges = infoResponse.getJokes().getIdRange();
                for (Map.Entry<String, int[]> entry : idRanges.entrySet()) {
                    int[] range = entry.getValue();

                    JdbcUtils.ejecutarDML("INSERT INTO language_ranges (language_code, start_id, end_id) "
                            + "VALUES ('" + entry.getKey() + "', " + range[0] + ", " + range[1] + ")");
                    System.out.println("Inserted idRange for language: " + entry.getKey());
                }

                System.out.println("Inserting jokes");
                int counter = 0;
                for (Map.Entry<String, int[]> entry : idRanges.entrySet()) {
                    int[] range = entry.getValue();

                    for (int i = range[0]; i <= range[1]; i++) {
                    	counter++;
                        if (counter % 120 == 0 && i != 0) {
                            Thread.sleep(60000);  
                        }

                        String url = "https://v2.jokeapi.dev/joke/Any?idRange=" + i + "," + i + "&lang=" + entry.getKey();
                        Joke joke = JsonUtils.readGeneric(url, Joke.class);

                        if (joke != null && joke.getId() != 0) {
                            String setup = (joke.getSetup() == null) ? "NULL" : "'" + joke.getSetup().replace("'", "''") + "'";
                            String delivery = (joke.getDelivery() == null) ? "NULL" : "'" + joke.getDelivery().replace("'", "''") + "'";
                            String jokeText = (joke.getJoke() == null) ? "NULL" : "'" + joke.getJoke().replace("'", "''") + "'";

                            String jokeInsertQuery = "INSERT INTO jokes (id, id_language, category_name, type_name, setup, delivery, joke) "
                                    + "VALUES (" + joke.getId() + ", '" + joke.getLang() + "', '" + joke.getCategory()
                                    + "', '" + joke.getType() + "', " + setup + ", " + delivery + ", " + jokeText + ")";
                            JdbcUtils.ejecutarDML(jokeInsertQuery);

                            insertJokeFlags(joke.getId(), joke.getLang(), joke.getFlags());
                            System.out.println("INSERTED " + counter);
                        } else {
                            System.out.println("Skipping joke with invalid ID: " + counter);
                        }
                    }
                }
            } else {
                return "Error: Could not retrieve information or API error occurred.";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            JdbcUtils.cerrarBbdd();
            System.out.println("Ended");
        }
        return "Ended";
    }

    private static void insertJokeFlags(int jokeId, String languageCode, Joke.Flags flags) {
        if (flags != null && jokeId != 0) {
            Map<String, Boolean> flagChecks = new LinkedHashMap<>();
            flagChecks.put("nsfw", flags.isNsfw());
            flagChecks.put("religious", flags.isReligious());
            flagChecks.put("political", flags.isPolitical());
            flagChecks.put("racist", flags.isRacist());
            flagChecks.put("sexist", flags.isSexist());
            flagChecks.put("explicit", flags.isExplicit());

            flagChecks.forEach((flagName, isFlagSet) -> {
                if (isFlagSet) {
                    JdbcUtils.ejecutarDML("INSERT INTO jokes_flags (id_joke, id_language, flag_name) VALUES (" + jokeId
                            + ", '" + languageCode + "', '" + flagName + "')");
                }
            });
        }
    }

    /**
     * Adds a new joke to the database based on the provided joke request.
     * Supports "single" and "twopart" joke types and inserts the relevant flags.
     *
     * @param jokeRequest The {@link JokeRequest} object containing joke details.
     * @return A message indicating the success or failure of the operation.
     */
	@PostMapping("/add-joke")
	public String addJoke(@RequestBody JokeRequest jokeRequest) {
		try {
			JdbcUtils.conexionBbdd(db, name, password);

			int lastJokeId = getLastJokeId();

			String setup = null;
			String delivery = null;
			String jokeText = null;

			if ("single".equalsIgnoreCase(jokeRequest.getType())) {
				jokeText = jokeRequest.getJoke();
			} else if ("twopart".equalsIgnoreCase(jokeRequest.getType())) {
				setup = jokeRequest.getSetup();
				delivery = jokeRequest.getDelivery();
			} else {
				return "Error: Invalid joke type. Valid types are 'single' or 'twopart'.";
			}

			String jokeInsertQuery = "INSERT INTO jokes (id, id_language, category_name, type_name, setup, delivery, joke) "
					+ "VALUES (" + (lastJokeId + 1) + ", '" + jokeRequest.getLanguage() + "', '"
					+ jokeRequest.getCategory() + "', '" + jokeRequest.getType() + "', "
					+ (setup != null ? "'" + setup.replace("'", "''") + "'" : "NULL") + ", "
					+ (delivery != null ? "'" + delivery.replace("'", "''") + "'" : "NULL") + ", "
					+ (jokeText != null ? "'" + jokeText.replace("'", "''") + "'" : "NULL") + ")";

			int rowsInserted = JdbcUtils.ejecutarDML(jokeInsertQuery);
			if (rowsInserted > 0) {
				insertJokeFlags(lastJokeId + 1, jokeRequest.getLanguage(), jokeRequest.getFlags());
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
			flags.forEach((flagName, isFlagSet) -> {
				if (isFlagSet) {
					JdbcUtils.ejecutarDML("INSERT INTO jokes_flags (id_joke, id_language, flag_name) VALUES (" + jokeId
							+ ", '" + languageCode + "', '" + flagName + "')");
				}
			});
		}
	}

	private int getLastJokeId() {
		int lastInsertedId = -1;
		try {
			String query = "SELECT MAX(id) FROM jokes";
			ResultSet rs = JdbcUtils.devolverQuery(query);
			if (rs != null && rs.next()) {
				lastInsertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lastInsertedId;
	}

    /**
     * Adds a new joke to the database using a prepared statement to prevent SQL injection.
     * Supports "single" and "twopart" joke types and inserts the relevant flags.
     *
     * @param jokeRequest The {@link JokeRequest} object containing joke details.
     * @return A message indicating the success or failure of the operation.
     */
	@PostMapping("/add-joke-prepared")
	public String addJokePrepared(@RequestBody JokeRequest jokeRequest) {
		try {
			JdbcUtils.conexionBbdd(db, name, password);

			int lastJokeId = getLastJokeId();

			String setup = null;
			String delivery = null;
			String jokeText = null;

			if ("single".equalsIgnoreCase(jokeRequest.getType())) {
				jokeText = jokeRequest.getJoke();
			} else if ("twopart".equalsIgnoreCase(jokeRequest.getType())) {
				setup = jokeRequest.getSetup();
				delivery = jokeRequest.getDelivery();
			} else {
				return "Error: Invalid joke type. Valid types are 'single' or 'twopart'.";
			}

			String jokeInsertQuery = "INSERT INTO jokes (id, id_language, category_name, type_name, setup, delivery, joke) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

			List<Object> params = new ArrayList<>();
			params.add(lastJokeId + 1);
			params.add(jokeRequest.getLanguage());
			params.add(jokeRequest.getCategory());
			params.add(jokeRequest.getType());
			params.add(setup != null ? setup : null);
			params.add(delivery != null ? delivery : null);
			params.add(jokeText != null ? jokeText : null);

			int rowsInserted = JdbcUtils.ejecutarPreparedStatement(jokeInsertQuery, params);
			if (rowsInserted > 0) {
				insertJokeFlags(lastJokeId + 1, jokeRequest.getLanguage(), jokeRequest.getFlags());
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

    /**
     * Searches for jokes in the database that match the given search text.
     *
     * @param searchText The text to search for in jokes.
     * @return A list of {@link Joke} objects containing jokes that match the search criteria.
     */
	@GetMapping("search-joke")
	public List<Joke> searchJokesByText(@RequestParam("text") String searchText) {
	    JdbcUtils.conexionBbdd(db, name, password);

	    List<Joke> jokesList = new ArrayList<>();

	    ResultSet rs = JdbcUtils.searchJokesByText(searchText);

	    try {
	        while (rs != null && rs.next()) {
	            int id = rs.getInt("id");
	            String category = rs.getString("category");
	            String type = rs.getString("type");
	            String setup = rs.getString("setup");
	            String delivery = rs.getString("delivery");
	            String jokeText = rs.getString("joke");
	            String lang = rs.getString("lang");

	            Flags flags = new Flags();
	            flags.setNsfw(rs.getBoolean("nsfw"));
	            flags.setReligious(rs.getBoolean("religious"));
	            flags.setPolitical(rs.getBoolean("political"));
	            flags.setRacist(rs.getBoolean("racist"));
	            flags.setSexist(rs.getBoolean("sexist"));
	            flags.setExplicit(rs.getBoolean("explicit"));

	            Joke joke = new Joke(id, category, type, setup, delivery, jokeText, flags, lang);
	            jokesList.add(joke);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        JdbcUtils.cerrarBbdd();
	    }

	    return jokesList;
	}

    /**
     * Retrieves all jokes from the database that do not have any associated flags.
     *
     * @return A list of {@link Joke} objects without any flags.
     */
	@GetMapping("no-flags-jokes")
	public List<Joke> getJokesWithoutFlags() {
	    JdbcUtils.conexionBbdd("jdbc:postgresql://localhost:5432/jokes", "postgres", "postgres");
	    List<Joke> jokesList = new ArrayList<>();
	    ResultSet rs = JdbcUtils.getJokesWithoutFlags();

	    try {
	        while (rs != null && rs.next()) {
	            int id = rs.getInt("id");
	            String category = rs.getString("category_name"); 
	            String type = rs.getString("type_name");  
	            String setup = rs.getString("setup");
	            String delivery = rs.getString("delivery");
	            String jokeText = rs.getString("joke");
	            String lang = rs.getString("id_language");

	            Joke joke = new Joke(id, category, type, setup, delivery, jokeText, null, lang);
	            jokesList.add(joke);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        JdbcUtils.cerrarBbdd();
	    }

	    return jokesList;
	}

}
