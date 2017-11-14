package com.xyzdrivers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class MemberEdit extends HttpServlet {

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
        String id = request.getParameter("id");
        System.out.println(id);
        Member member = AdminDB.getMemberByID(id);
        request.setAttribute("member", member);
        RequestDispatcher view = request.getRequestDispatcher("/adminEditMember.jsp");
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
        try{
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String address = request.getParameter("address");

            String dobString = request.getParameter("dob");
            System.out.println("DOB:" + dobString);
            java.util.Date dobUtil = new SimpleDateFormat("yyyy-MM-dd").parse(dobString);
            Date dob = new Date(dobUtil.getTime());

            String dorString = request.getParameter("dob");
            java.util.Date dorUtil = new SimpleDateFormat("yyyy-MM-dd").parse(dorString);
            Date dor = new Date(dorUtil.getTime());

            String status = request.getParameter("status");
            float balance = Float.parseFloat(request.getParameter("balance"));

            Member member = new Member(id, name, address, dob, dor, status, balance);
            AdminDB.updateMember(member);

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
