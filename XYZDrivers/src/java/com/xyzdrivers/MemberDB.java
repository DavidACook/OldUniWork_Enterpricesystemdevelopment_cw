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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.xyzdrivers.models.Claim;
import com.xyzdrivers.models.Payment;
import java.util.ArrayList;
/**
 *  This class will supply the back end functionality for the member dash board.
 *  It will provide the bridge between the UI and the database
 * @author Charlie Arnold
 */
public class MemberDB {
    private String userID;
    
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
    
    public MemberDB(String memID) {
        userID = memID;
    }

    //This method allows the user to pay their entire outstanding balance
    //  It checks if the user has a balance > 0
    //  If balance is 10, type is Fee
    //  Else type is ClaimShare
    //IMPORTANT status is left alone as an Admin needs to approve the payment
    //          before reinstating user
    public static String makePayment(String memID){
        String retString = "";
        double amount, balance = 0.0;
        String type;
        int id = 1;
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        Date date;
        //Establish connections with database
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();
            //Get member balance
            String sql ="SELECT \"balance\" FROM APP.\"MEMBERS\" WHERE \"id\" = '" + memID +"'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            balance = rs.getDouble(1);

            //If bal is 0, reutrn
            if(balance == 0.0){
                return "No payments to be made!";
            }
            else{
                //Generate query to check if memebr has made claim in last year
                date = Calendar.getInstance().getTime();
                Calendar dateMoreOneYear = Calendar.getInstance();
                dateMoreOneYear.add(Calendar.YEAR, -1);
                String curDate = df.format(dateMoreOneYear.getTime());
                sql ="SELECT COUNT(*) FROM APP.\"PAYMENTS\" "
                + "WHERE \"mem_id\" = '" + memID +"' "
                + "AND \"type_of_payment\" = 'FEE' "
                + "AND \"date\" >=  '" + curDate + "'";
                
                rs = stmt.executeQuery(sql);
                rs.next();
                int numPayments = rs.getInt(1);

                //If member has paid anual fee in last year
                if(numPayments > 0){
                    type = "CLAIM_SHARE";
                    amount = balance;
                }
                else{ 
                    type = "FEE";
                    amount = 10.00;
                }
            }
              
            //Generate paymentID
            sql ="SELECT COUNT(*) FROM APP.\"PAYMENTS\"";
            rs = stmt.executeQuery(sql);
            rs.next();
            id += rs.getInt(1);
 
            //Get current date
            date = Calendar.getInstance().getTime();
            Calendar dateMoreOneYear = Calendar.getInstance();
            String curDate = df.format(dateMoreOneYear.getTime());
            //Get current time
            String curTime = time.format(date.getTime());
            
            retString = "Paying " +type+ " of " +amount;
            
            //Generate SQL stmt
            sql = "INSERT INTO APP.\"PAYMENTS\" "
                + "VALUES (" +id+ ", '" +memID+ "', '"
                + type+ "', " +Double.toString(amount)+ ", '"
                + curDate+ "', '" +curTime+ "')";
            PreparedStatement prepStat = con.prepareStatement(sql);       
            prepStat.executeUpdate();

            //Update member balance to 0 
            balance -= amount;
            sql = "UPDATE APP.\"MEMBERS\" SET \"balance\" = "+ Double.toString(balance)
                    + " WHERE \"id\" = '" + memID +"'";
            prepStat = con.prepareStatement(sql);       
            prepStat.executeUpdate();
            
            //Close connections with database
            stmt.close(); 
            prepStat.close();
            con.close();
        }
        catch (Exception s){
            System.out.println("SQL stmt is not executed!");
            s.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        return retString;      
    }
    
    //This method gets the users name
    public static String getName(String memID){
        String name = "";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            con = getConnection();
            stmt = con.createStatement();
            String sql = "SELECT \"name\" FROM APP.\"MEMBERS\""
                    + " WHERE \"id\" = '" +memID+ "'";
            rs = stmt.executeQuery(sql);
            rs.next();
            name = rs.getString(1);
            
            stmt.close(); 
            rs.close();
            con.close();
        }
        catch (Exception s){
            System.out.println("SQL stmt is not executed!");
            s.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        return name;
    }
    
    //This method acts as the back end for making a claim
    //It will take mem_id, rationale and amount as input
    //It will generate id based on number of claims in CLAIMS table
    //It will get the date from the computer
    //It will set the status too APPLIED
    //TODO check user hasnt made more than two claims
    public static String makeClaim(String memID, String rationale, double amount){
        String retString = "Claim unsuccessfull";
        //Ensure ammount is x.00 format
        amount = ((double)((int)(amount*100)))/100;
        //Establish connections with database
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int id=1;

        try{
            con = getConnection();
            retString += memberCanMakeClaim(memID);
            if(retString.equals("Claim unsuccessfull")){
                retString = "Claim successfull";
                //Get number of claims
                stmt = con.createStatement();
                String sql ="SELECT COUNT(*) FROM APP.\"CLAIMS\"";
                rs = stmt.executeQuery(sql);
                //id = numClaims + 
                rs.next();
                id += rs.getInt(1);

                //Get date from computer
                DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
                java.util.Date date = Calendar.getInstance().getTime();
                String curDate = df.format(date);

                //Generate sql string
                String sqlInsert = "INSERT INTO APP.\"CLAIMS\" "
                                + "VALUES (" + id+ ", '" +memID+ "', '"
                            + curDate+"', '" + rationale +"', 'APPLIED', "
                            + Double.toString(amount) + ")";
                //Prepare stmt
                PreparedStatement prepStat = con.prepareStatement(sqlInsert);       
                //Execute update
                prepStat.executeUpdate();

                //Close connections with database
                stmt.close(); 
                prepStat.close();
                con.close();
            }
        }
        catch (Exception s){
            System.out.println("SQL stmt is not executed!");
            s.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        return retString;
    }
    
    //This method checks if a member is able to make a claim
    //A member can only make two claims a year
    //A member can only make a claim after 6 months of registration
    //A member cannot make a claim if their account has been suspended
    public static String memberCanMakeClaim(String memID){
        xyzdriverswsapplication.NewWebService_Service service = new xyzdriverswsapplication.NewWebService_Service();
        xyzdriverswsapplication.NewWebService port = service.getNewWebServicePort();
        return port.claimCheck(memID);
    }
    
    //This method will be called to get a string containing the memebrs balance
    public static String checkBalance(String memID){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql ="SELECT \"balance\" FROM APP.\"MEMBERS\" WHERE \"id\" = '" + memID +"'";
        
        try{
            con = getConnection();
            //Get Data
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            ResultSetMetaData metaData =  rs.getMetaData();
            rs.next();
            
            //SQL should only return one object.
            String balance = rs.getObject(1).toString();
            //Output results
            System.out.print("Balance: £" + balance);
            
            //Close connections
            rs.close();
            stmt.close(); 		
            con.close();   
            System.out.println("\n");
            return balance;
        }
        catch (Exception s){
            System.out.println("SQL stmt is not executed!");
            s.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        return "";
    }
   
    //This method accesses the database, searches for all Claims from 
        //a specific member. It then returns these as an arrayList
    public static ArrayList<Claim> getClaimList(String memID){
        ArrayList<Claim> claimList = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
 
        try{
            con = getConnection();
            String sql = "SELECT * FROM APP.\"CLAIMS\" "
                    + "WHERE \"mem_id\" = '" + memID + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            
            //Create Claim objects
            while(rs.next()){
                int id = rs.getInt("id");
                String mem_id = rs.getString("mem_id");
                java.sql.Date date = rs.getDate("date");
                String rationale = rs.getString("rationale");
                String status = rs.getString("status");
                float amount = rs.getFloat("amount");
                Claim claim = new Claim(id, mem_id, date, rationale, status, amount);
                claimList.add(claim);     
            } 
        }
        catch (Exception s){
            System.out.println("SQL stmt is not executed!");
            s.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        return claimList;
    }
    
    public static ArrayList<Payment> getPaymentList(String memID){
        ArrayList<Payment> paymentList = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
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
            String sql = "SELECT * FROM APP.\"PAYMENTS\" "
                    + "WHERE \"mem_id\" = '" + memID + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            
        
            while(rs.next()){
                int id = rs.getInt("id");
                String mem_id = rs.getString("mem_id");
                String type = rs.getString("type_of_payment");
                float amount = rs.getFloat("amount");
                java.sql.Date date = rs.getDate("date");
                java.sql.Time time = rs.getTime("time");
                         
                Payment payment = new Payment(id, mem_id, type, amount, date, time);
                paymentList.add(payment);     
            } 
        }
        catch (SQLException s){
            System.out.println("SQL stmt is not executed!");
            s.printStackTrace();
        } finally {
            closeConnection(con, stmt, rs);
        }
        return paymentList;
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
