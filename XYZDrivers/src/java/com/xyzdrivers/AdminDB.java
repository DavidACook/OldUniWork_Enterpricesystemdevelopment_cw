/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xyzdrivers;

import com.xyzdrivers.models.Claim;
import com.xyzdrivers.models.DBConnection;
import com.xyzdrivers.models.Member;
import com.xyzdrivers.models.Payment;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Colin Berry
 */
public class AdminDB {
    
    private static Connection getConnection() throws Exception{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection con = DriverManager.getConnection(DBConnection.HOST, DBConnection.USER, DBConnection.PASS);
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

    static Claim getClaimByID(int id) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();
            
            stmt = con.createStatement();
            String query = String.format("SELECT * FROM Claims WHERE \"id\" = %d", id);
            rs = stmt.executeQuery(query);
            
            return getClaimList(rs).get(0);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        return null;
    }
    
    public static ArrayList<Payment> getAllPayments(){
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
    
    public static ArrayList<Payment> getAllPaymentsBetweenDates(Date date1, Date date2){
        ArrayList<Payment> paymentList = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();
            
            stmt = con.createStatement();
            String query = String.format("SELECT * FROM Payments WHERE \"date\" BETWEEN '%s' AND '%s'", date1, date2);
            rs = stmt.executeQuery(query);
            
            return getPaymentList(rs);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        
        return paymentList;
    }
    
    public static ArrayList<Payment> getAllPaymentsByType(String type){
        ArrayList<Payment> paymentList = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();
            
            stmt = con.createStatement();
            String query = String.format("SELECT * FROM Payments WHERE \"type_of_payment\" = '%s'", type);
            rs = stmt.executeQuery(query);
            
            return getPaymentList(rs);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        
        return paymentList;
    }
    
    public static ArrayList<Payment> getAllPaymentsByMember(String id){
        ArrayList<Payment> paymentList = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();
            
            stmt = con.createStatement();
            String query = String.format("SELECT * FROM Payments WHERE \"mem_id\" = '%s'", id);
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
    
    //Code for applying member annual fees
    public static void applyAnnualFees(){
        //Get List of members
        ArrayList<Member> memList = getMemberList();
        //For Every Member
        for(Member member : memList){
            //IF Balance == 0
            if(member.getBalance() == 0.0){
                //If(Count Payments Type-Fee in last year == 0)
                if(memberPaidFee(member.getId()) == 0){
                    //Set Balance to 10
                    member.setBalance((float)10.00);
                    member.setStatus("SUSPENDED");
                    updateMember(member);
                }    
            }
        }//End loop
    }
      
    //This method returns 1 if the member has paid their fee for the year
        //or 0 if they havent
    private static int memberPaidFee(String memID){
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int numFees = 0;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(DBConnection.HOST,DBConnection.USER,DBConnection.PASS);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDB.class.getName()).log(Level.SEVERE, null, ex);
        }
         catch (SQLException ex) {
            Logger.getLogger(AdminDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            statement = con.createStatement();
            DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
            java.util.Date date = Calendar.getInstance().getTime();
            Calendar dateMoreOneYear = Calendar.getInstance();
            dateMoreOneYear.add(Calendar.YEAR, -1);
            String curDate = df.format(dateMoreOneYear.getTime());
            String sql ="SELECT COUNT(*) FROM APP.\"PAYMENTS\" "
                    + "WHERE \"mem_id\" = '" + memID +"' "
                    + "AND \"type_of_payment\" = 'FEE' "
                    + "AND \"date\" >=  '" + curDate + "'";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            numFees = resultSet.getInt(1);
        }
        catch (SQLException s){
            System.out.println("SQL statement is not executed!");
            s.printStackTrace();
        }     
        return numFees;    
    }
    
    //This returns an array list of all members
    private static ArrayList<Member> getMemberList(){
        ArrayList<Member> memberList = new ArrayList<>();
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(DBConnection.HOST,DBConnection.USER,DBConnection.PASS);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDB.class.getName()).log(Level.SEVERE, null, ex);
        }
         catch (SQLException ex) {
            Logger.getLogger(AdminDB.class.getName()).log(Level.SEVERE, null, ex);
        }    
        try{
            String sql = "SELECT * FROM APP.\"MEMBERS\" ";
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            
            //Create Member objects
            while(rs.next()){
                String memID = rs.getString("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                java.sql.Date dob = rs.getDate("dob");
                java.sql.Date dor = rs.getDate("dor"); 
                String status = rs.getString("status");
                float balance = rs.getFloat("balance");
                Member member = new Member(memID, name, address, dob, dor, status, balance);
                memberList.add(member);     
            } 
        }
        catch (SQLException s){
            System.out.println("SQL statement is not executed!");
            s.printStackTrace();
        }
        return memberList;
    }
}
