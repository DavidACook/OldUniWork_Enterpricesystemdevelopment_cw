/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xyzdrivers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Colin Berry
 */
public class Register extends HttpServlet {

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
        RequestDispatcher view = request.getRequestDispatcher("register.jsp");
        view.forward(request, response);
        String[] info = new String[2];
        info[0] = request.getParameter("name");
        info[1] = request.getParameter("address");
        info[2] = request.getParameter("dob");
        
        try {
            registerMember(info);
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registerMember(String[] info) throws SQLException{
        String host = "jdbc:derby//localhost:1527/ESD";
        String user = "root";
        
        Connection con = DriverManager.getConnection(host, user, null);
         
        String name = info[0];
        String address = info[1];
        String dob = info[2];
        LocalDate registerDate = LocalDate.now();
        String status = "APPLIED";
        double balance = 0;
        
        String id = generateID(name);

        String members = "INSERT INTO 'MEMBERS' ('id','name','address','dob','dor','status','balance')"
                + "VALUES ('" + id + "','" + name + "','" + address + "','" + dob + "','" + registerDate + "','" + status + "','" + balance + "')";

        String users = "INSERT INTO 'USERS' ('id','password','status'" + 
                "VALUES ('" + id + "','" + generatePassword() + "','" + status + "')";
        
        Statement state;
        try {
            state = con.createStatement();
            state.executeQuery(members);
            state.executeQuery(users);
            
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    public String generateID(String name){
        StringBuilder id = new StringBuilder();
        
        id.append(name.charAt(0)).append("-");
        
        int space = name.indexOf(' ');
        
        for(int i = space + 1; i < name.length(); i++){
            id.append(name.charAt(i));
        }
        
        return id.toString();
    }
    
    
    public String generatePassword(){
        String chars = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
        StringBuilder password = new StringBuilder();
        Random ran = new Random();
        for(int i = 0; i < 6; i++){
            password.append(chars.charAt(ran.nextInt(6)));
        }
        
        
        return password.toString();
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
