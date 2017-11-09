/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xyzdrivers.models;

import com.xyzdrivers.AdminDB;
import java.sql.Date;
import java.util.ArrayList;

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
    
    public ArrayList<Claim> getAllClaims(){
        return AdminDB.getAllClaimsByMember(id);
    }
    
    @Override
    public String toString(){
        return String.format("ID %s - Name %s - Address %s - DOB %s - DOR %s - Status %s - Balance %f",
                id, name, address, dob, dor, status, balance);
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDor() {
        return dor;
    }

    public void setDor(Date dor) {
        this.dor = dor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getBalance() {
        return balance;
    }
    
    public void setBalance(float balance) {
        this.balance = balance;
    }
    
}
