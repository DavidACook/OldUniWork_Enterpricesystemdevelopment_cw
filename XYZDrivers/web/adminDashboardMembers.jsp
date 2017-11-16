<%-- 
    Document   : adminDashboardMembers.jsp
    Created on : 13-Nov-2017, 14:47:07
    Author     : Colin Berry
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="com.xyzdrivers.models.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="styles/table.css" />
<link rel="stylesheet" type="text/css" href="styles/main.css" />
<script type="text/javascript" src="javascript/jquery-3.2.1.js"></script>
<script type="text/javascript" src="javascript/clickableTable.js"></script>
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
        <form id="form" onsubmit="submitRow()" action="AdminDashboard/MemberEdit">
            <input type="submit" value="Edit Member"/>
            <table id="table">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Date of Birth</th>
                    <th>Date of Registration</th>
                    <th>Status</th>
                    <th>Balance</th>
                </tr>
                <% for(Member m : (ArrayList<Member>) request.getAttribute("membersList")) { %>
                <tr>
                    <td><%= m.getId() %></td>
                    <td><%= m.getName() %></td>
                    <td><%= m.getAddress() %></td>
                    <td><%= m.getDob() %></td>
                    <td><%= m.getDor() %></td>
                    <td><%= m.getStatus() %></td>
                    <td><%= m.getBalance() %></td>
                </tr>
                <% } %>
            </table>
            <p>
                
            </p>
        </form>
    </body>
</html>

