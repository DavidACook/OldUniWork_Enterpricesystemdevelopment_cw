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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                ArrayList<Claim> claims = getFilteredClaimsList(filter, request);
                request.setAttribute("claimsList", claims);
                jsp = "/adminDashboardClaims.jsp";
            }
            if(type.equals("payments")){
                ArrayList<Payment> payments = getFilteredPaymentsList(filter, request);
                request.setAttribute("paymentsList", payments);
                jsp = "/adminDashboardPayments.jsp";
            }
            if(type.equals("fees")){
                AdminDB.applyAnnualFees();
                jsp = "/adminDashboard.jsp";
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
    private ArrayList<Claim> getFilteredClaimsList(String filter, HttpServletRequest request){
        ArrayList<Claim> claims;
        
        if(filter != null){
            switch(filter){
                case "member":
                    String mem_id = request.getParameter("id");
                    claims = AdminDB.getAllClaimsByMember(mem_id);
                    break;
                case "status":
                    String status = request.getParameter("status");
                    claims = AdminDB.getAllClaimsByStatus(status);
                    break;
                default:
                    claims = AdminDB.getAllClaims();
            }
        } else {
            claims = AdminDB.getAllClaims();
        }
        
        return claims;
    }
    private ArrayList<Payment> getFilteredPaymentsList(String filter, HttpServletRequest request){
        ArrayList<Payment> payments;
        
        if(filter != null){
            switch(filter){
                case "type":
                    String type = request.getParameter("paymentType");
                    payments = AdminDB.getAllPaymentsByType(type);
                    break;
                case "member":
                    String id = request.getParameter("id");
                    payments = AdminDB.getAllPaymentsByMember(id);
                    break;
                case "dates":
                    try{
                        String dateString = request.getParameter("date1");
                        java.util.Date dateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                        Date date1 = new Date(dateUtil.getTime());


                        dateString = request.getParameter("date2");
                        dateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                        Date date2 = new Date(dateUtil.getTime()); 

                        payments = AdminDB.getAllPaymentsBetweenDates(date1, date2);
                    } catch (ParseException e){
                        payments = AdminDB.getAllPayments();
                    }
                    break;
                default:
                    payments = AdminDB.getAllPayments();
            }
        } else {
            payments = AdminDB.getAllPayments();
        }
        
        return payments;
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
