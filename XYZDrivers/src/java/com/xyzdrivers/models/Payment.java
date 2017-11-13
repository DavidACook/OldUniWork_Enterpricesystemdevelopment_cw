/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xyzdrivers.models;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Colin Berry
 */
public class Payment {
    int id;
    String mem_id;
    String type_of_payment;
    float amount;
    Date date;
    Time time;
    
    public Payment(int id, String mem_id, String type_of_payment, float amount, Date date, Time time){
        this.id = id;
        this.mem_id = mem_id;
        this.type_of_payment = type_of_payment;
        this.amount = amount;
        this.date = date;
        this.time = time;
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

    public String getType_of_payment() {
        return type_of_payment;
    }

    public void setType_of_payment(String type_of_payment) {
        this.type_of_payment = type_of_payment;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
    
    
}
