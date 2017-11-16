package com.xyzdrivers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.xyzdrivers.models.DBConnection;
import com.xyzdrivers.models.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
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
        RequestDispatcher view = request.getRequestDispatcher("login.jsp");
        view.forward(request, response);
    }
    
    public static User getUser(String uname, String pass){
        String dbUser, dbPass, dbStatus;
        String query = String.format("SELECT * FROM users WHERE \"id\" = '%s' AND \"password\" = '%s'",
                uname, pass);
 
        //Establish connections
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection(DBConnection.HOST, DBConnection.USER, DBConnection.PASS);
            Statement stmt = (Statement) con.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            //check over result set for the entered id and password.
            while(rs.next()){
                dbUser = rs.getString("id");
                dbPass = rs.getString("password");
                dbStatus = rs.getString("status");
                
                User user = new User(dbUser, dbPass, dbStatus);
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
        String uname = request.getParameter("username");
        String pass = request.getParameter("password");
        
        try{
            User user = getUser(uname, pass);
            if (user != null){
                System.out.println("FOUND USER");
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                
                if(user.getStatus().equals("ADMIN")){
                    response.sendRedirect(request.getContextPath() + "/AdminDashboard/View");
                } else if(user.getStatus().equals("APPROVED")){
                    response.sendRedirect(request.getContextPath() + "/MemberDashboard");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
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
