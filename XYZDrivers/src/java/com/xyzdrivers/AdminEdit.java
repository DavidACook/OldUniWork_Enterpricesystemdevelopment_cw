/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xyzdrivers;

import com.xyzdrivers.models.Claim;
import com.xyzdrivers.models.Member;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Colin Berry
 */
public class AdminEdit extends HttpServlet {

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
        String type = request.getParameter("type");
        String action = request.getParameter("action");
        Claim claim;
        Member member;
        
        if(type != null){
            if(type.equals("claim")){
                int id = Integer.parseInt(request.getParameter("id"));
                if(action != null){
                    switch(action){
                        case "approve":
                            claim = AdminDB.getClaimByID(id);
                            claim.setStatus("APPROVED");
                            AdminDB.updateClaim(claim);
                            response.sendRedirect("View?type=claims");
                            return;
                        case "reject":
                            claim = AdminDB.getClaimByID(id);
                            claim.setStatus("REJECTED");
                            AdminDB.updateClaim(claim);
                            response.sendRedirect("View?type=claims");
                            return;
                        default:
                            claim = AdminDB.getClaimByID(id);
                            request.setAttribute("claim", claim);
                            RequestDispatcher view = request.getRequestDispatcher("/adminEditClaim.jsp");
                            view.forward(request, response);
                            return;
                    }
                }
            } else if(type.equals("member")) {
                String id = request.getParameter("id");
                if(action != null){
                    switch(action){
                        case "approve":
                            member = AdminDB.getMemberByID(id);
                            member.setStatus("APPROVED");
                            AdminDB.updateMember(member);
                            response.sendRedirect("View?type=members");
                            return;
                        case "reject":
                            member = AdminDB.getMemberByID(id);
                            member.setStatus("REJECTED");
                            AdminDB.updateMember(member);
                            response.sendRedirect("View?type=members");
                            return;
                        default:
                            member = AdminDB.getMemberByID(id);
                            request.setAttribute("member", member);
                            RequestDispatcher view = request.getRequestDispatcher("/adminEditMember.jsp");
                            view.forward(request, response);
                            return;
                    }
                }
            }
        }
        
        response.sendRedirect("View?type=claims");
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
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            String mem_id = request.getParameter("mem_id");

            String dateString = request.getParameter("date");
            java.util.Date dateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            Date date = new Date(dateUtil.getTime());

            String rationale = request.getParameter("rationale");
            String status = request.getParameter("status");
            float amount = Float.parseFloat(request.getParameter("amount"));

            Claim claim = new Claim(id, mem_id, date, rationale, status, amount);
            AdminDB.updateClaim(claim);

        } catch (ParseException e){
            e.printStackTrace();
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
