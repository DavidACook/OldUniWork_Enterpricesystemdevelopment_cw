<%-- 
    Document   : memberDashboard
    Created on : 06-Nov-2017, 11:26:26
    Author     : Colin Berry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="common.jsp" %>
<link rel="stylesheet" type="text/css" href="styles/main.css" />
<link rel="stylesheet" type="text/css" href="styles/centered.css" />
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/logo/favicon.ico" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Dashboard</title>
    </head>
    <body>
        <h1>Welcome ${name}</h1>
        <form method="post" action="MemberDashboard">      
            <input type="submit" name="type" value="View Claims">
            <input type="submit" name="type" value="View Payments">           
            <input type="submit" name="type" value="Make Claim">
            <input type="submit" name="type" value="Make Payment">
            <input type="submit" name="type" value="Check Balance">
        </form>
        ${output}
    </body>
</html>
