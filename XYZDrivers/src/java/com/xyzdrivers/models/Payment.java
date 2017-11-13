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
    
    public Payment(){
        
    }
}
