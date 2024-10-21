package com.ruben.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

	}

	public static void consultaBD1() throws ClassNotFoundException, SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/datos1";
			String usuario = "postgres";
			String password = "pwd";
			Connection con = DriverManager.getConnection(url, usuario, password);
			Statement statement = con.createStatement();
			String sentenciaSQL = "SELECT nombre, descripcion FROM evento ORDER BY nombre";
			ResultSet rs = statement.executeQuery(sentenciaSQL);
			System.out.println("Nombre" + "\t" + "E-mail");
			System.out.println("-----------------------------------------");
			while (rs.next()) {
				System.out.println(rs.getString(1) + "\t " + rs.getString(2));
			}
			rs.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
