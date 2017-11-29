/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XYZDriversWSApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author Colin Berry
 */
@WebService(serviceName = "NewWebService")
@Stateless()
public class NewWebService {
    
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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "claimCheck")
    public String claimCheck(@WebParam(name = "mem_id") String memID) {
        String retString = "";
        memID.trim();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
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
                + "AND \"status\" = 'APPROVED' "
                + "AND \"date\" >=  '" + curDate + "'";
        
        //Member status sql
        String sqlMemStatus = "SELECT \"status\" FROM APP.\"MEMBERS\" "
                + "WHERE \"id\" = '" + memID +"'";
        
        //Establish connections
        
        try{
            con = getConnection();
            //Check DOR > 6months ago
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlDor);
            rs.next();
            LocalDate resultDate = rs.getDate(1).toLocalDate();
            resultDate.plusMonths(6);
            if(date.before(java.sql.Date.valueOf(resultDate.plusMonths(6)))){
                retString = "<br>You cannot make claims until you have been reigstered for 6 months!";
            }
                
            //Check user hasnt made for than two claims
            rs = stmt.executeQuery(sqlNumClaims);
            rs.next();
            int numClaims = rs.getInt(1);
            if(numClaims >= 2){
                System.out.println();
                retString = "<br>You can only have 2 approved claims per year!";
            }
            
            //Check user is APPROVED
            rs = stmt.executeQuery(sqlMemStatus);
            rs.next();
            String memStatus = rs.getString(1);
            if(!memStatus.equals("APPROVED")){
                retString = "<br>You must be an APPROVED member to make claims!";
            }
            
            //Close connections
            rs.close();
            stmt.close(); 		
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
}
