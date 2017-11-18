/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xyzdrivers;

import com.xyzdrivers.models.Claim;
import com.xyzdrivers.models.Member;
import com.xyzdrivers.models.Payment;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Colin Berry
 */
public class AdminDashboard extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String type = request.getParameter("type");
        System.out.println(type);
        String jsp = "/adminDashboard.jsp";
        String filter = request.getParameter("filter");
        
        if(type != null){
            if(type.equals("members")){
                ArrayList<Member> members = getFilteredMembersList(filter, request);
                request.setAttribute("membersList", members);
                jsp = "/adminDashboardMembers.jsp";
            }
            if(type.equals("claims")){
                ArrayList<Claim> claims = getFilteredClaimsList(filter);
                request.setAttribute("claimsList", claims);
                jsp = "/adminDashboardClaims.jsp";
            }
            if(type.equals("payments")){
                ArrayList<Payment> payments = getFilteredPaymentsList(filter);
                request.setAttribute("paymentsList", payments);
                jsp = "/adminDashboardPayments.jsp";
            }
        }
        System.out.println(jsp);
        RequestDispatcher view = request.getRequestDispatcher(jsp);
        view.forward(request, response);
    }
    
    private ArrayList<Member> getFilteredMembersList(String filter, HttpServletRequest request){
        ArrayList<Member> members;
        
        if(filter != null){
            switch(filter){
                case "status":
                    members = AdminDB.getAllMembersByStatus(request.getParameter("status"));
                    break;
                case "outstanding":
                    members = AdminDB.getAllMembersOutstanding();
                    break;
                default:
                    members = AdminDB.getAllMembers();
            }
        } else {
            members = AdminDB.getAllMembers();
        }
        
        return members;
    }
    private ArrayList<Claim> getFilteredClaimsList(String filter){
        return AdminDB.getAllClaims();
    }
    private ArrayList<Payment> getFilteredPaymentsList(String filter){
        return AdminDB.getAllPayments();
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
