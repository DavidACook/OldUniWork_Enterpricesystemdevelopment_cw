/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xyzdrivers;

import com.xyzdrivers.models.Claim;
import com.xyzdrivers.models.Member;
import com.xyzdrivers.models.Payment;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Colin Berry
 */
public class AdminDB {
    
    public static void main(String[] args) {
        System.out.println(" MEMBERS ");
        System.out.println("\nList");
        ArrayList<Member> memberList = getAllMembers();
        for(Member m : memberList){
            System.out.println(m);
        }
        
        Member member = getMemberByID("m-wood");
        System.out.println("\nBy ID");
        System.out.println(member);
        
        System.out.println("\nUpdate");
        member.setBalance(member.getBalance() + 1);
        updateMember(member);
        member = getMemberByID("m-wood");
        System.out.println(member);
        
        System.out.println("\n CLAIMS ");
        System.out.println("\nList");
        ArrayList<Claim> claims = getAllClaims();
        for(Claim c : claims){
            System.out.println(c);
        }
        
        System.out.println("\nList By ID");
        claims = getAllClaimsByMember("me-aydin");
        for(Claim c : claims){
            System.out.println(c);
        }
        
        System.out.println("\nList By Status");
        claims = getAllClaimsByStatus("APPROVED");
        for(Claim c : claims){
            System.out.println(c);
        }
        
        System.out.println("\nUpdate");
        Claim claim = claims.get(0);
        claim.setAmount(claim.getAmount() + 1);
        updateClaim(claim);
        claims = getAllClaimsByStatus("APPROVED");
        System.out.println(claims.get(0));
        
        System.out.println("\nAnnual Revenue");
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 0, 1);
        Date yearAgo = new Date(cal.getTimeInMillis());
        float revenue = getAnnualRevenue(yearAgo);
        System.out.println(revenue);
    }
    
    private static final String HOST = "jdbc:derby://localhost:1527/webapp";
    private static final String USERNAME = "app";
    private static final String PASSWORD = "app";
    
    private static Connection getConnection() throws Exception{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        return con;
    }
    
    private static void closeConnection(Connection con, Statement stmt, ResultSet rs){
        try{
            if(con != null){
                con.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(rs != null){
                rs.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private static ArrayList<Member> getMemberList(ResultSet rs) throws Exception{
        ArrayList<Member> memberList = new ArrayList<>();
        
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
        
        return memberList;
    }
    
    private static ArrayList<Claim> getClaimList(ResultSet rs) throws Exception{
        ArrayList<Claim> claimList = new ArrayList<>();
        
        while(rs.next()){
                int id = rs.getInt("id");
                String mem_id = rs.getString("mem_id");
                Date date = rs.getDate("date");
                String rationale = rs.getString("rationale");
                String status = rs.getString("status");
                float amount = rs.getFloat("amount");
                
                Claim claim = new Claim(id, mem_id, date, rationale, status, amount);
                claimList.add(claim);
            }
        
        return claimList;
    }
    
    private static ArrayList<Payment> getPaymentList(ResultSet rs) throws Exception{
        ArrayList<Payment> paymentList = new ArrayList<>();
        
        while(rs.next()){
            int id = rs.getInt("id");
            String mem_id = rs.getString("mem_id");
            String type_of_payment = rs.getString("type_of_payment");
            float amount = rs.getFloat("amount");
            Date date = rs.getDate("date");
            Time time = rs.getTime("time");
            
            Payment payment = new Payment(id, mem_id, type_of_payment, amount, date, time);
            paymentList.add(payment);
        }
        
        return paymentList;
    }
    
    public static ArrayList<Member> getAllMembers(){
        ArrayList<Member> memberList = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();
            
            stmt = con.createStatement();
            String query = "SELECT * FROM Members";
            rs = stmt.executeQuery(query);
            
            return getMemberList(rs);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        
        return memberList;
    }
    
    public static ArrayList<Member> getAllMembersOutstanding(){
        ArrayList<Member> memberList = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();
            
            stmt = con.createStatement();
            String query = "SELECT * FROM Members WHERE \"balance\" > 0";
            rs = stmt.executeQuery(query);
            
            return getMemberList(rs);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        
        return memberList;
    }
    
    public static ArrayList<Member> getAllMembersByStatus(String status){
        ArrayList<Member> memberList = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();
            
            stmt = con.createStatement();
            String query = String.format("SELECT * FROM Members WHERE \"status\" = '%s'", status);
            rs = stmt.executeQuery(query);
            
            return getMemberList(rs);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        
        return memberList;
    }
    
    public static Member getMemberByID(String id){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();
            
            stmt = con.createStatement();
            String query = String.format("SELECT * FROM Members WHERE \"id\" = '%s'", id);
            rs = stmt.executeQuery(query);
            
            return getMemberList(rs).get(0);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        return null;
    }
    
    public static void updateMember(Member member){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();
            
            stmt = con.createStatement();
            String query = String.format("UPDATE Members SET \"name\" = '%s',"
                    + "\"address\" = '%s',"
                    + "\"status\" = '%s',"
                    + "\"balance\" = %f"
                    + " WHERE \"id\" = '%s'",
                    member.getName(), member.getAddress(), member.getStatus(), member.getBalance(), member.getId());
            stmt.executeUpdate(query);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
    }
    
    public static ArrayList<Claim> getAllClaims(){
        ArrayList<Claim> claims = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();
            
            stmt = con.createStatement();
            String query = "SELECT * FROM Claims";
            rs = stmt.executeQuery(query);
            
            return getClaimList(rs);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        return claims;
    }
    
    public static ArrayList<Claim> getAllClaimsByStatus(String status){
        ArrayList<Claim> claims = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();
            
            stmt = con.createStatement();
            String query = String.format("SELECT * FROM Claims WHERE \"status\" = '%s'", status);
            rs = stmt.executeQuery(query);
            
            return getClaimList(rs);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        return claims;
    }

    public static ArrayList<Claim> getAllClaimsByMember(String mem_id) {
        ArrayList<Claim> claims = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();
            
            stmt = con.createStatement();
            String query = String.format("SELECT * FROM Claims WHERE \"mem_id\" = '%s'", mem_id);
            rs = stmt.executeQuery(query);
            
            return getClaimList(rs);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        return claims;
    }
    
    public static void updateClaim(Claim claim){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();
            
            stmt = con.createStatement();
            String query = String.format("UPDATE Claims SET \"mem_id\" = '%s',"
                    + "\"date\" = '%s',"
                    + "\"rationale\" = '%s',"
                    + "\"status\" = '%s',"
                    + "\"amount\" = %f"
                    + " WHERE \"id\" = %d",
                    claim.getMem_id(), claim.getDate(), claim.getRationale(), claim.getStatus(), claim.getAmount(), claim.getId());
            stmt.executeUpdate(query);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
    }
    
    public ArrayList<Payment> getAllPayments(){
        ArrayList<Payment> paymentList = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();
            
            stmt = con.createStatement();
            String query = "SELECT * FROM Payments";
            rs = stmt.executeQuery(query);
            
            return getPaymentList(rs);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        
        return paymentList;
    }
    
    public static ArrayList<Payment> getAllPaymentsUnapproved(){
        ArrayList<Payment> paymentList = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();
            
            stmt = con.createStatement();
            String query = "SELECT * FROM Payments WHERE \"";
            rs = stmt.executeQuery(query);
            
            return getPaymentList(rs);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        
        return paymentList;
    }
    
    public static void updatePayment(Payment payment){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();
            
            stmt = con.createStatement();
            String query = String.format("UPDATE Payments SET \"mem_id\" = '%s',"
                    + "\"type_of_payment\" = '%s',"
                    + "\"amount\" = %f,"
                    + "\"date\" = '%s',"
                    + "\"time\" = '%s' "
                    + "WHERE \"id\" = %d",
                    payment.getMem_id(), payment.getType_of_payment(), payment.getAmount(),
                    payment.getDate(), payment.getTime(), payment.getId());
            stmt.executeUpdate(query);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
    }
    
    // Overload function to deal with the 'other' Date format
    public static float getAnnualRevenue(java.util.Date date){
        Date newDate = new Date(date.getTime());
        return getAnnualRevenue(newDate);
    }
    
    public static float getAnnualRevenue(Date date){
        float totalRevenue = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        // Set end date to a year from now
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        cal.add(Calendar.YEAR, 1);
        Date nextYear = new Date(cal.getTimeInMillis());
        
        try {
            con = getConnection();
            
            stmt = con.createStatement();
            String query = String.format("SELECT * FROM Payments WHERE "
                    + "\"date\" BETWEEN '%s' AND '%s'", date.toString(), nextYear.toString());
            rs = stmt.executeQuery(query);
            
            while(rs.next()){
                totalRevenue += rs.getFloat("amount");
            }
            
            return totalRevenue;
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        
        return 0;
        
    }
}
