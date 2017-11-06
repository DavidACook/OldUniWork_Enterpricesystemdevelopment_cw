/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xyzdrivers.models;

import java.sql.Date;

/**
 *
 * @author Colin Berry
 */
public class Member {
    String id;
    String name;
    String address;
    Date dob;
    Date dor;
    String status;
    float balance;
    
    public Member(String id, String name, String address, Date dob, Date dor, String status, float balance){
        this.id = id;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.status = status;
        this.balance = balance;
    }
    
    @Override
    public String toString(){
        return String.format("ID %s - Name %s - Address %s - DOB %s - DOR %s - Status %s - Balance %f",
                id, name, address, dob, dor, status, balance);
    }
    
}
