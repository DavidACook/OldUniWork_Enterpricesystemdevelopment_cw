package com.xyzdrivers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  This class will supply the back end functionality for the member dash board.
 *  It will provide the bridge between the UI and the database
 * @author Charlie Arnold
 */
public class MemberDB {
    private String userID;
    
    public MemberDB(String memID) {
        userID = memID;
    }

    //This method acts as the back end for making a claim
    //It will take mem_id, rationale and amount as input
    //It will generate id based on number of claims in CLAIMS table
    //It will get the date from the computer
    //It will set the status too APPLIED
    public static void makeClaim(String memID, String rationale, double amount){
        //Ensure ammount is x.00 format
        amount = ((double)((int)(amount*100)))/100;
        //Establish connections with database
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int id=1;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/myUse",null, null);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MemberDB.class.getName()).log(Level.SEVERE, null, ex);
        }
         catch (SQLException ex) {
            Logger.getLogger(MemberDB.class.getName()).log(Level.SEVERE, null, ex);
        }    
        try{
        //Get number of claims
        statement = con.createStatement();
        String sql ="SELECT COUNT(*) FROM APP.\"CLAIMS\"";
        resultSet = statement.executeQuery(sql);
        //id = numClaims + 
        resultSet.next();
        id += resultSet.getInt(1);
        
        //Get date from computer
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        java.util.Date date = Calendar.getInstance().getTime();
        String curDate = df.format(date);
        
        //Generate sql string
        String sqlInsert = "INSERT INTO APP.\"CLAIMS\" "
                        + "VALUES (" + id+ ", '" +memID+ "', '"
                    + curDate+"', '" + rationale +"', 'APPLIED', "
                    + Double.toString(amount) + ")";
        //Prepare statement
        PreparedStatement prepStat = con.prepareStatement(sqlInsert);       
        //Execute update
        prepStat.executeUpdate();
        
        //Close connections with database
        statement.close(); 
        prepStat.close();
        con.close();
        }
        catch (SQLException s){
            System.out.println("SQL statement is not executed!");
            s.printStackTrace();
        }
    }
    
    //This method will be called to get a string containing the memebrs balance
    public String checkBalance(String memID){
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql ="SELECT \"balance\" FROM APP.\"MEMBERS\" WHERE \"id\" = '" + memID +"'";
        //Establish connections
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/myUse",null, null);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MemberDB.class.getName()).log(Level.SEVERE, null, ex);
        }
         catch (SQLException ex) {
            Logger.getLogger(MemberDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            //Get Data
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData =  resultSet.getMetaData();
            resultSet.next();
            
            //SQL should only return one object.
            String balance = resultSet.getObject(1).toString();
            //Output results
            System.out.print("Balance: £" + balance);
            
            //Close connections
            resultSet.close();
            statement.close(); 		
            con.close();   
            System.out.println("\n");
            return balance;
        }
        catch (SQLException s){
            System.out.println("SQL statement is not executed!");
            s.printStackTrace();
        }
        return "";
    }
    
    //This method returns a list of all claims made by a certain member
    public String listAllClaims(String memID){
        return listAllDBAcess(true, memID);
    }
    
    //This method returns a list of all claims made by a certain member
    public String listAllPayments(String memID){
        return listAllDBAcess(false, memID);
    }
    
    //This methods establishes a connection with the database and lists either 
    //all claims or all payments made by this member.
    //For claims, choice == true
    //For payments choice == false;
    public String listAllDBAcess(boolean choice, String memID){
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String results = "", temp = "";
        String sql = (choice)?
                "SELECT * FROM APP.\"CLAIMS\" WHERE \"mem_id\" = '" + memID +"'":
                "SELECT * FROM APP.\"PAYMENTS\" WHERE \"mem_id\" = '" + memID +"'";
        //Establish connections
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/myUse",null, null);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MemberDB.class.getName()).log(Level.SEVERE, null, ex);
        }
         catch (SQLException ex) {
            Logger.getLogger(MemberDB.class.getName()).log(Level.SEVERE, null, ex);
        }    
        try{
            //Get Data
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);        
            ResultSetMetaData metaData =  resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            //Print collum names
            for (int i = 1; i <= numberOfColumns; i++)   
                System.out.print(metaData.getColumnName(i)+"\t\t");     
            System.out.println();

            //Get data from result set
            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++){
                    //Get data
                    temp = resultSet.getObject(i) +"\t\t";
                    //Update return string
                    results += temp;
                    //Output data
                    System.out.print(temp);
                }
                results+="\n";
                System.out.println();
            }
            //Close connections
            resultSet.close();
            statement.close(); 		
            con.close();   
            System.out.println("\n");
            return results;
        }
        catch (SQLException s){
            System.out.println("SQL statement is not executed!");
            s.printStackTrace();
        }
        return results;
    }
     
    //This method checks if a double is in currency format i.e 2 decimals after digit
    public boolean isCurrencyFormat(double d){
        String text = Double.toString(Math.abs(d));
        int decimalPlaces = text.length() - text.indexOf('.') - 1;
        if(decimalPlaces <= 2)
            return true;
        return false;
    }
   
}
