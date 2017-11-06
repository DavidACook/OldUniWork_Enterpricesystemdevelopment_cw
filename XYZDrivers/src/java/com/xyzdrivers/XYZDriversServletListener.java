/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xyzdrivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.*;

/**
 *
 * @author Chris
 * Servlet Context class to initiate database connection at start of web app.
 * can add more init params if required.
 */
public class XYZDriversServletListener implements ServletContextListener {
    private Connection conn = null;
    
    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();
        String db = sc.getInitParameter("...");
        //need to add name for database here
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://locahlhost:1527/..."+db.trim(),",<USERNAME>","<PASSWORD>");
            //add db name to end of string
        }
        catch(ClassNotFoundException | SQLException e){
            sc.setAttribute("error", e);
        }
        sc.setAttribute("connection",conn);
        
    }
    
    public void contextDestroyed(ServletContextEvent event){
        try{conn.close();} catch(SQLException e){}
    }
}
