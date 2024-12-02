/**
 * Utility class for managing JDBC connections, queries, and stored procedure calls.
 */
package com.edu.ruben.JokesApp.Utils;

import java.sql.*;
import java.util.List;

public class JdbcUtils {

    static Connection con;
    static Statement statement;
    public static PreparedStatement stmt;
    static CallableStatement cstmt;
    static ResultSet rs;

    /**
     * Establishes a connection to the database.
     *
     * @param url      the database URL
     * @param usuario  the username for the database
     * @param password the password for the database
     * @return true if the connection was successful, false otherwise
     */
    public static boolean conexionBbdd(String url, String usuario, String password) {
        try {
            con = DriverManager.getConnection(url, usuario, password);
            statement = con.createStatement();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Closes the database connection and statement if they are open.
     */
    public static void cerrarBbdd() {
        try {
            if (con != null && statement != null && !con.isClosed() && !statement.isClosed()) {
                statement.close();
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes a SQL query and returns the result set.
     *
     * @param sql the SQL query to execute
     * @return a ResultSet containing the query results, or null if an error occurs
     */
    public static ResultSet devolverQuery(String sql) {
        try {
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Executes a Data Manipulation Language (DML) statement (INSERT, UPDATE, DELETE).
     *
     * @param sql the SQL DML statement to execute
     * @return the number of rows affected, or -1 if an error occurs
     */
    public static int ejecutarDML(String sql) {
        try {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Executes a prepared statement with parameters.
     *
     * @param sql        the SQL statement with placeholders
     * @param parametros the parameters to bind to the placeholders
     * @return the number of rows affected, or 0 if an error occurs
     */
    public static int ejecutarPreparedStatement(String sql, List<Object> parametros) {
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            for (int i = 0; i < parametros.size(); i++) {
                stmt.setObject(i + 1, parametros.get(i));
            }
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Calls a stored procedure to retrieve jokes without flags.
     *
     * @return a ResultSet containing the jokes, or null if an error occurs
     */
    public static ResultSet getJokesWithoutFlags() {
        try {
            String sql = "{call get_jokes_without_flags()}";
            CallableStatement cstmt = con.prepareCall(sql);
            return cstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Calls a stored procedure to search for jokes by text.
     *
     * @param searchText the text to search for
     * @return a ResultSet containing the search results, or null if an error occurs
     */
    public static ResultSet searchJokesByText(String searchText) {
        try {
            String sql = "{call search_jokes_by_text(?)}";
            CallableStatement cstmt = con.prepareCall(sql);
            cstmt.setString(1, searchText);
            return cstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Executes a stored procedure with multiple parameters.
     *
     * @param metodo     the procedure name and placeholders
     * @param parametros the parameters to bind to the placeholders
     * @return a ResultSet containing the results, or null if an error occurs
     */
    public static ResultSet resultSetCallableStatement(String metodo, Object... parametros) {
        if (countMatches(metodo, '?') != parametros.length)
            return null;
        try {
            cstmt = con.prepareCall("{call " + metodo + "}");
            for (int i = 1; i <= parametros.length; i++) {
                cstmt.setObject(i, parametros[i - 1]);
            }
            return cstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves the last inserted ID from the database.
     *
     * @return the ID of the last inserted record, or -1 if an error occurs
     */
    public static int obtenerUltimoIdInsertado() {
        int lastInsertedId = -1;
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT LASTVAL()");
            if (rs.next()) {
                lastInsertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastInsertedId;
    }

    /**
     * Counts the occurrences of a character in a string.
     *
     * @param cadena          the string to search
     * @param caracterBuscado the character to count
     * @return the number of occurrences of the character
     */
    private static int countMatches(String cadena, char caracterBuscado) {
        return (int) cadena.chars().filter(e -> e == caracterBuscado).count();
    }
}
