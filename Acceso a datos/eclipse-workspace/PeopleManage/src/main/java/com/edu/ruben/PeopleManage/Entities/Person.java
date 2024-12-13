package com.edu.ruben.PeopleManage.Entities;

public class Person {
    private int number;
    private String name;
    private String surnames;
    private String address;
    private String phone;

    public Person(String name, String surnames, String address, String phone) {
        this.name = name;
        this.surnames = surnames;
        this.address = address;
        this.phone = phone;
    }
    
    public Person(int number, String name, String surnames, String address, String phone) {
        this.number = number;
        this.name = name;
        this.surnames = surnames;
        this.address = address;
        this.phone = phone;
    }

    // Getters y Setters
    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurnames() { return surnames; }
    public void setSurnames(String surnames) { this.surnames = surnames; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
