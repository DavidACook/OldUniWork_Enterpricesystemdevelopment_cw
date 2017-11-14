package com.xyzdrivers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 *
 * @author Colin Berry
 */
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
                
        if(user.equals("admin") && pass.equals("admin")){
            //admin doesn't need checking, gets redirected
            RequestDispatcher rs = request.getRequestDispatcher("/AdminDashboard");
            rs.forward(request, response);
        }
        else if(validateUser(user, pass)){
            //check if user exists in database
            request.setAttribute("memID", user);
            RequestDispatcher rs = request.getRequestDispatcher("/MemberDashboard");
            rs.forward(request, response);
        }
        else{
            //user doesn't exist, they need to register
            RequestDispatcher rs = request.getRequestDispatcher("/Register");
            rs.forward(request, response);
        }
    }
    
    public static boolean validateUser(String user, String pass){
        boolean ch = false; //Set correct user to false.
        String dbUser, dbPass;
        String host = "jdbc:derby://localhost:1527/webapp";
        String db = "app";
        String dataPass = "app";
        String query = "SELECT * FROM APP.users";
 
        //Establish connections
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection(host, db, dataPass);
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            //check over result set for the entered id and password.
            while(rs.next()){
                dbUser = rs.getString("id");
                dbPass = rs.getString("password");
                //if the user exists, ch is set to true and they can login.
                if(dbUser.equals(user) && dbPass.equals(pass)){
                    ch = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        return ch;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
}
