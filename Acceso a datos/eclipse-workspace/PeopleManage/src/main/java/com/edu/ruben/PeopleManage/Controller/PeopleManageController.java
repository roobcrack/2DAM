package com.edu.ruben.PeopleManage.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.ruben.PeopleManage.Entities.Person;
import com.edu.ruben.PeopleManage.Utils.JdbcUtils;
import com.edu.ruben.PeopleManage.Utils.JsonUtils;

@RestController
@RequestMapping("/api")
public class PeopleManageController {
    public static String db = "jdbc:postgresql://localhost:5432/people";
    public static String nameDB = "postgres";
    public static String passwordDB = "postgres";

    /**
     * Gets all people from the database (PEOPLE, CLIENTS, FUNCTIONARIES)
     * @return returns the json list of the request
     */
    @GetMapping("/getPeople")
    public String getAllPeople() {
        JdbcUtils.connectDb(db, nameDB, passwordDB);
        
        // SQL query to fetch all people
        String sql = "SELECT * FROM People";
        ResultSet rs = JdbcUtils.returnQuery(sql);
        
        // List to store the result as Person objects
        List<Person> peopleList = new ArrayList<>();
        
        try {
            // Process each record from the ResultSet
            while (rs.next()) {
                int number = rs.getInt("num");
                String name = rs.getString("name");
                String surnames = rs.getString("surnames");
                String address = rs.getString("address");
                String phone = rs.getString("phone");

                // Create a Person object and add it to the list
                Person person = new Person(number, name, surnames, address, phone);
                peopleList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        JdbcUtils.closeDb();
        System.out.println("People get correctly");
        return JsonUtils.toJson(peopleList);
    }
    
    @GetMapping("/setPeople")
    public static void setNewPerson(@RequestParam("name") String name, @RequestParam("surnames") String surnames,
                                    @RequestParam("address") String address,  @RequestParam("phone") String phone) {
        String sql = "INSERT INTO people(name, surnames, address, phone) "
        		+ "VALUES(?, ?, ?, ?)";
        List<Object> dataInsert = List.of(name, surnames, address, phone);
        executeInsert(sql, dataInsert);
    }

    @GetMapping("/setClient")
    public static void setNewClient(@RequestParam("name") String name, @RequestParam("surnames") String surnames,
                                    @RequestParam("address") String address, @RequestParam("phone") String phone,
                                    @RequestParam("numaccount") String numaccount, @RequestParam("stateclient") String stateclient,
                                    @RequestParam("typeclient") String typeclient) {
        String sql = "INSERT INTO clients(name, surnames, address, phone, numaccount, stateclient, typeclient) "
        		+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
        List<Object> dataInsert = List.of(name, surnames, address, phone, numaccount, stateclient, typeclient);
        executeInsert(sql, dataInsert);
    }
    
    @GetMapping("/setFunctionary")
    public static void setNewFunctionary(@RequestParam("name") String name, @RequestParam("surnames") String surnames,
                                    @RequestParam("address") String address,  @RequestParam("phone") String phone,
                                    @RequestParam("charge_group") String chargegroup, @RequestParam("charge_doce") String chargedoce,
                                    @RequestParam("department") String department, @RequestParam("date_ingress") String dateingress) {
    	String sql = "INSERT INTO functionaries(name, surnames, address, phone, charge_group, charge_doce, department, data_ingress) "
    			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        List<Object> dataInsert = List.of(name, surnames, address, phone, chargegroup, chargedoce, department, dateingress);
        executeInsert(sql, dataInsert);
    }

    private static void executeInsert(String sql, List<Object> dataInsert) {
        try {
            // Connect to the database
            JdbcUtils.connectDb(db, nameDB, passwordDB);
            
            // Execute the prepared statement
            JdbcUtils.executedPreparedStatement(sql, dataInsert);
            System.out.println("Data inserted successfully.");
        } catch (Exception e) {
            System.err.println("Error inserting data: " + e.getMessage());
        } finally {
            // Ensure the database connection is closed
            JdbcUtils.closeDb();
        }
    }
    
    
    public static void updatePerson(Person person) {
    	String sql = "UPDATE person SET name="+person.getName()+", surnames="+person.getAddress()+", address="+person.getAddress()+", phone="+person.getPhone()+" WHERE number = "+person.getNumber();
    	
    }
}
