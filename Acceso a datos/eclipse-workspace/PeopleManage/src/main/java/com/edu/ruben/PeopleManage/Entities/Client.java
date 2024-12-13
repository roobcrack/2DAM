package com.edu.ruben.PeopleManage.Entities;

import java.util.Date;

public class Client extends Person {
    private String accountNumber; 
    private String state;          
    private String clientType;    

    public Client(String name, String surnames, String address, String phone, Date birthDate, 
                  String accountNumber, String state, String clientType) {
        super(name, surnames, address, phone);  
        this.accountNumber = accountNumber;
        this.state = state;
        this.clientType = clientType;
    }

    public Client(int number, String name, String surnames, String address, String phone, Date birthDate, 
                  String accountNumber, String state, String clientType) {
        super(number, name, surnames, address, phone); 
        this.accountNumber = accountNumber;
        this.state = state;
        this.clientType = clientType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }
}
