<%-- 
    Document   : adminDashboard
    Created on : 06-Nov-2017, 11:15:59
    Author     : Colin Berry
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.xyzdrivers.models.Member"%>
<%@include file="common.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
</body>