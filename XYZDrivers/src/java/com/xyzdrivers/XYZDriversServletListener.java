/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xyzdrivers;

import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.*;

/**
 *
 * @author Chris
 */
public class XYZDriversServletListener implements ServletContextListener {
    private Connection conn = null;
    
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();
        String db = sc.getInitParameter("");
        
        try{
            Class.forName("org.apache.derby.jdbc.");
            conn = .getConnection("jdbc:derby://locahlhost:1527/"+db.trim(),",<USERNAME>","<PASSWORD>");
        }
        catch(ClassNotFoundException | SQLException e){
            sc.setAttribute("error", e);
        }
        sc.setAttribute("connection",conn);
        
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent event){
        try{conn.close();} catch(SQLException e){}
    }
}
