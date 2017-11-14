/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xyzdrivers;

import com.xyzdrivers.models.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
        
    }
    
    public void destroy() {
            
    }
    
    public void doFilter(ServletRequest req, ServletResponse res,
                    FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String url = request.getServletPath();
        
        if(session == null || session.getAttribute("user") == null){
            response.sendRedirect(request.getContextPath() + "/Login");
        } else {
            User user = (User) session.getAttribute("user");
            if(user.getStatus().equals("ADMIN")){
                chain.doFilter(req, res);
            } else {
                response.sendRedirect(request.getContextPath() + "/Login");
            }
        }
    }
}