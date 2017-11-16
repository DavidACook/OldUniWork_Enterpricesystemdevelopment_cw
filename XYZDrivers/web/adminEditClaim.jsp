<%-- 
    Document   : claimEdit.jsp
    Created on : 13-Nov-2017, 14:57:22
    Author     : Colin Berry
--%>

<%@page import="com.xyzdrivers.models.Claim"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/centered.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/edittable.css" />
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/logo/favicon.ico" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Claim</title>
    </head>
    
    <script>
        function onSubmit(){
            alert("Saved Claim Data");
        }
    </script>
    
    <body>
        <h1>Editing Claim</h1>
        <form onsubmit="onSubmit()" action="ClaimEdit" method="POST">
            <input name="id" type="hidden" value="${claim.id}"/>
            <table id="claim" border="1" cellpadding="5">
                <tr>
                    <th>ID: </th>
                    <td><c:out value="${claim.id}"/></td>
                </tr>
                <tr>
                    <th>Member ID: </th>
                    <td>
                        <input name="mem_id" type="text" value="${claim.mem_id}"/>
                    </td>
                </tr>
                <tr>
                    <th>Date: </th>
                    <td>
                        <input name="date" type="date" value="${claim.date}"/>
                    </td>
                </tr>
                <tr>
                    <th>Rationale: </th>
                    <td>
                        <input name="rationale" type="text" value="${claim.rationale}"/>
                    </td>
                </tr>
                <tr>
                    <th>Status: </th>
                    <td>
                        <input name="status" type="text" value="${claim.status}"/>
                    </td>
                </tr>
                <tr>
                    <th>Amount: </th>
                    <td>
                        <input name="amount" type="number" step="0.01" value="${claim.amount}"/>
                    </td>
                </tr>
            </table>
            <input type="submit" value="Save"/>
        </form>
        <form action="../AdminDashboard">
            <input type="hidden" name="type" value="View Claims"/>
            <input type="submit" value="Back to Admin Dashboard"/>
        </form>
    </body>
</html>
