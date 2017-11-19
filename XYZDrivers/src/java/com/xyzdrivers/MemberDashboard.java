/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xyzdrivers;

import com.xyzdrivers.models.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Colin Berry
 */
public class MemberDashboard extends HttpServlet {

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
        //String memID = request.getParameter("memID");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String memID = user.getId();
        String type = request.getParameter("type");
        String output = "";
        String jsp = "memberDashboard.jsp";
        if(type != null){
            switch(type){
                case "Return": break;
                case "Check Balance": output = "Member balance: " 
                        + MemberDB.checkBalance(memID); break;
                case "View Claims": output = MemberDB.listAllClaims(memID); break;
                case "View Payments": output = MemberDB.listAllPayments(memID); break;
                case "Make Claim": jsp = "makeClaim.jsp"; break;
                case "Make Payment": output = MemberDB.makePayment(memID); break;
                case "Submit Claim": String rationale = request.getParameter("rationale");
                    double amount = Double.valueOf(request.getParameter("amount"));
                    output = MemberDB.makeClaim(memID, rationale, amount);    
                    break;   
                default: output = "error"; break;
            }
            request.setAttribute("output", output);
        }
        request.setAttribute("memID", memID);
        request.setAttribute("name", MemberDB.getName(memID));
        RequestDispatcher view = request.getRequestDispatcher(jsp);
        view.forward(request, response);
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
