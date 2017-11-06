/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xyzdrivers;

import com.xyzdrivers.models.Member;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Colin Berry
 */
public class AdminDB {
    
    public static void main(String[] args) {
        ArrayList<Member> memberList = getAllMembers();
        for(Member m : memberList){
            System.out.println(m);
        }
    }
    
    public static ArrayList<Member> getAllMembers(){
        ArrayList<Member> memberList = new ArrayList<>();
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/webapp", "app", "app");
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Members");
            while(rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                Date dob = rs.getDate("dob");
                Date dor = rs.getDate("dor");
                String status = rs.getString("status");
                float balance = rs.getFloat("balance");
                
                Member member = new Member(id, name, address, dob, dor, status, balance);
                memberList.add(member);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return memberList;
    }
    
}
