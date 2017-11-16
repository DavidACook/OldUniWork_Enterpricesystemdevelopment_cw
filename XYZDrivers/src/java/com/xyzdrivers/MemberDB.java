package com.xyzdrivers;

import com.xyzdrivers.models.DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

    //This method allows the user to pay their entire outstanding balance
    //  It checks if the user has a balance > 0
    //  If balance is 10, type is Fee
    //  Else type is ClaimShare
    //IMPORTANT status is left alone as an Admin needs to approve the payment
    //          before reinstating user
    public void makePayment(String memID){
        //Get member balance
        
        //If bal is 0, reutrn
        //Else if bal is 10, type is Fee
        //Else type is ClaimsShare
        
        //Generate paymentID
        //Get current date
        //Get current time
        
        //Generate SQL statement
        //Run Sql statement
        //Update member balance to 0       
    }
    
    //This method acts as the back end for making a claim
    //It will take mem_id, rationale and amount as input
    //It will generate id based on number of claims in CLAIMS table
    //It will get the date from the computer
    //It will set the status too APPLIED
    //TODO check user hasnt made more than two claims
    public static boolean makeClaim(String memID, String rationale, double amount){
        boolean success = false;
        //Ensure ammount is x.00 format
        amount = ((double)((int)(amount*100)))/100;
        //Establish connections with database
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int id=1;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(DBConnection.HOST,DBConnection.USER,DBConnection.PASS);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MemberDB.class.getName()).log(Level.SEVERE, null, ex);
        }
         catch (SQLException ex) {
            Logger.getLogger(MemberDB.class.getName()).log(Level.SEVERE, null, ex);
        }    
        try{
            if(memberCanMakeClaim(memID)){
                success = true;
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
        }
        catch (SQLException s){
            System.out.println("SQL statement is not executed!");
            s.printStackTrace();
        }
        return success;
    }
    
    //This method checks if a member is able to make a claim
    //A member can only make two claims a year
    //A member can only make a claim after 6 months of registration
    //A member cannot make a claim if their account has been suspended
    public static boolean memberCanMakeClaim(String memID){
        boolean canClaim = true;
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        //Date of register SQL
        String sqlDor ="SELECT \"dor\" FROM APP.\"MEMBERS\" "
                + "WHERE \"id\" = '" + memID +"'";
        
        //Number of claims sql
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        java.util.Date date = Calendar.getInstance().getTime();
        Calendar dateMoreOneYear = Calendar.getInstance();
        dateMoreOneYear.add(Calendar.YEAR, -1);
        String curDate = df.format(dateMoreOneYear.getTime());
        String sqlNumClaims ="SELECT COUNT(*) FROM APP.\"CLAIMS\" "
                + "WHERE \"mem_id\" = '" + memID +"' "
                + "AND \"date\" >=  '" + curDate + "'";
        
        //Member status sql
        String sqlMemStatus = "SELECT \"status\" FROM APP.\"MEMBERS\" "
                + "WHERE \"id\" = '" + memID +"'";
        
        //Establish connections
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(DBConnection.HOST,DBConnection.USER,DBConnection.PASS);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MemberDB.class.getName()).log(Level.SEVERE, null, ex);
        }
         catch (SQLException ex) {
            Logger.getLogger(MemberDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            //Check DOR > 6months ago
            statement = con.createStatement();
            resultSet = statement.executeQuery(sqlDor);
            resultSet.next();
            LocalDate resultDate = resultSet.getDate(1).toLocalDate();
            resultDate.plusMonths(6);
            if(date.before(java.sql.Date.valueOf(resultDate.plusMonths(6)))){
                canClaim = false;
            }
                
            //Check user hasnt made for than two claims
            resultSet = statement.executeQuery(sqlNumClaims);
            resultSet.next();
            int numClaims = resultSet.getInt(1);
            if(numClaims >= 2){
                canClaim = false;
            }
            
            //Check user is APPROVED
            resultSet = statement.executeQuery(sqlMemStatus);
            resultSet.next();
            String memStatus = resultSet.getString(1);
            if(!memStatus.equals("APPROVED")){
                canClaim = false;
            }
            
            //Close connections
            resultSet.close();
            statement.close(); 		
            con.close();   
        }
        catch (SQLException s){
            System.out.println("SQL statement is not executed!");
            s.printStackTrace();
        }
        return canClaim;
    }
    
    //This method will be called to get a string containing the memebrs balance
    public static String checkBalance(String memID){
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql ="SELECT \"balance\" FROM APP.\"MEMBERS\" WHERE \"id\" = '" + memID +"'";
        //Establish connections
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(DBConnection.HOST,DBConnection.USER,DBConnection.PASS);
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
    public static String listAllClaims(String memID){
        return listAllDBAcess(true, memID);
    }
    
    //This method returns a list of all claims made by a certain member
    public static String listAllPayments(String memID){
        return listAllDBAcess(false, memID);
    }
    
    //This methods establishes a connection with the database and lists either 
    //all claims or all payments made by this member.
    //For claims, choice == true
    //For payments choice == false;
    public static String listAllDBAcess(boolean choice, String memID){
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
            con = DriverManager.getConnection(DBConnection.HOST,DBConnection.USER,DBConnection.PASS);
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
                results+="<p>";
                System.out.println();
            }
            //Close connections
            resultSet.close();
            statement.close(); 		
            con.close();   
            System.out.println("<p>");
            return results;
        }
        catch (SQLException s){
            System.out.println("SQL statement is not executed!");
            s.printStackTrace();
        }
        return results;
    }
     
    //This method checks if a double is in currency format i.e 2 decimals after digit
    public static boolean isCurrencyFormat(double d){
        String text = Double.toString(Math.abs(d));
        int decimalPlaces = text.length() - text.indexOf('.') - 1;
        if(decimalPlaces <= 2)
            return true;
        return false;
    }
   
}
