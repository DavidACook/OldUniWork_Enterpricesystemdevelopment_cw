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
public class Claim {
    int id;
    String mem_id;
    Date date;
    String rationale;
    String status;
    float amount;
    
    public Claim(int id, String mem_id, Date date, String rationale, String status, float amount){
        this.id = id;
        this.mem_id = mem_id;
        this.date = date;
        this.rationale = rationale;
        this.status = status;
        this.amount = amount;
    }
    
    @Override
    public String toString(){
        String strAmount = Float.toString(((float)((int)(amount * 100)))/100);
        return String.format("ID %d - Mem_id %s - Date %s - Rationale %s - Status %s - Amount %s", id, mem_id, date, rationale, status, strAmount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMem_id() {
        return mem_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRationale() {
        return rationale;
    }

    public void setRationale(String rationale) {
        this.rationale = rationale;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    
    
}
