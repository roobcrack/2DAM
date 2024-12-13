package com.edu.ruben.PeopleManage.Utils;

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
    public static boolean connectDb(String url, String usuario, String password) {
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
    public static void closeDb() {
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
    public static ResultSet returnQuery(String sql) {
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
    public static int executeDML(String sql) {
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
    public static int executedPreparedStatement(String sql, List<Object> parameters) {
		if (countMatches(sql, '?') != parameters.size()) {
	    	try {
	            PreparedStatement stmt = con.prepareStatement(sql);
	            for (int i = 0; i < parameters.size(); i++) {
	                stmt.setObject(i + 1, parameters.get(i));
	            }
	            return stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return 0;
	    	}
		}
		return 0;
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
