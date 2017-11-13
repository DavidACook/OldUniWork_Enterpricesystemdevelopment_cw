<%-- 
    Document   : adminDashboardClaims.jsp
    Created on : 13-Nov-2017, 14:55:25
    Author     : Colin Berry
--%>

<%@page import="com.xyzdrivers.models.Claim"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.xyzdrivers.models.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="styles/table.css" />
<link rel="stylesheet" type="text/css" href="styles/main.css" />
<script type="text/javascript" src="javascript/jquery-3.2.1.js"></script>
<script type="text/javascript" src="javascript/clickableTable.js" ></script>
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
        <form id="form" onsubmit="submitRow()" action="AdminDashboard/ClaimEdit">
            <table id="table">
                <tr>
                    <th>ID</th>
                    <th>Member ID</th>
                    <th>Date</th>
                    <th>Rationale</th>
                    <th>Status</th>
                    <th>Amount</th>
                </tr>
                <% for(Claim c : (ArrayList<Claim>) request.getAttribute("claimsList")) { %>
                <tr>
                    <td><%= c.getId() %></td>
                    <td><%= c.getMem_id() %></td>
                    <td><%= c.getDate() %></td>
                    <td><%= c.getRationale() %></td>
                    <td><%= c.getStatus() %></td>
                    <td><%= c.getAmount() %></td>
                </tr>
                <% } %>
            </table>
            <p>
                <input type="submit" value="Edit Claim"/>
            </p>
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