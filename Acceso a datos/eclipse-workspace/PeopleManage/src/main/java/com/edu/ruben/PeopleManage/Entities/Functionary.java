package com.edu.ruben.PeopleManage.Entities;

import java.util.Date;

public class Functionary extends Person {
    private String chargeGroup;  
    private String chargeCode;  
    private String department;
    private Date dateIngress;

    public Functionary(String name, String surnames, String address, String phone, 
                       String chargeGroup, String chargeCode, String department, Date dateIngress) {
        super(name, surnames, address, phone); 
        this.chargeGroup = chargeGroup;
        this.chargeCode = chargeCode;
        this.department = department;
        this.dateIngress = dateIngress;
    }

    public Functionary(int number, String name, String surnames, String address, String phone, 
                       String chargeGroup, String chargeCode, String department, Date dateIngress) {
        super(number, name, surnames, address, phone);
        this.chargeGroup = chargeGroup;
        this.chargeCode = chargeCode;
        this.department = department;
        this.dateIngress = dateIngress;
    }

    public String getChargeGroup() {
        return chargeGroup;
    }

    public void setChargeGroup(String chargeGroup) {
        this.chargeGroup = chargeGroup;
    }

    public String getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(String chargeCode) {
        this.chargeCode = chargeCode;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getDateIngress() {
        return dateIngress;
    }

    public void setDateIngress(Date dateIngress) {
        this.dateIngress = dateIngress;
    }
}
