<%-- 
    Document   : adminDashboard
    Created on : 06-Nov-2017, 11:15:59
    Author     : Colin Berry
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.xyzdrivers.models.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
    </head>
    <body>
        <h1>Admin Dashboard</h1>
        <form action="AdminDashboard">
            <input type="submit" name="type" value="View Members">
            <input type="submit" name="type" value="View Claims">
        </form>
          <a id="Navigate" href="./index.html">
            <input 
              type="button"
              id="homeButton"
              style="

                background-image: url(http://cdn3.blogsdna.com/wp-content/uploads/2010/03/Windows-Phone-7-Series-Icons-Pack.png);
                background-repeat: no-repeat;
                background-position: -272px -112px;
                cursor:pointer;
                height: 40px;
                width: 40px;
                border-radius: 26px;
                border-style: solid;
                border-color:#000;
                border-width: 3px;" title="Navigate"
              />  
          </a>
    </body>
</html>
