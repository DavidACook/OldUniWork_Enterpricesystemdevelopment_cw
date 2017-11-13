<%-- 
    Document   : claimEdit.jsp
    Created on : 13-Nov-2017, 14:57:22
    Author     : Colin Berry
--%>

<%@page import="com.xyzdrivers.models.Claim"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Claim</title>
    </head>
    <body>
        <h1>Editing Claim</h1>
        <form action="ClaimEdit" method="POST">
            <% Claim claim = (Claim) request.getAttribute("claim"); %>
            <input name="id" type="hidden" value="<%= claim.getId() %>"/>
            <table id="claim" border="1" cellpadding="5">
                <tr>
                    <th>ID: </th>
                    <td><%= claim.getId() %></td>
                </tr>
                <tr>
                    <th>Member ID: </th>
                    <td>
                        <input name="mem_id" type="text" value="<%= claim.getMem_id() %>"/>
                    </td>
                </tr>
                <tr>
                    <th>Date: </th>
                    <td>
                        <input name="date" type="date" value="<%= claim.getDate() %>"/>
                    </td>
                </tr>
                <tr>
                    <th>Rationale: </th>
                    <td>
                        <input name="rationale" type="text" value="<%= claim.getRationale() %>"/>
                    </td>
                </tr>
                <tr>
                    <th>Status: </th>
                    <td>
                        <input name="status" type="text" value="<%= claim.getStatus() %>"/>
                    </td>
                </tr>
                <tr>
                    <th>Amount: </th>
                    <td>
                        <input name="amount" type="number" value="<%= claim.getAmount() %>"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Save"/>
                    </td>
                </tr>
            </table>
        </form>
        <form action="../AdminDashboard">
            <input type="hidden" name="type" value="View Claims"/>
            <input type="submit" value="Back to Admin Dashboard"/>
        </form>
    </body>
</html>
