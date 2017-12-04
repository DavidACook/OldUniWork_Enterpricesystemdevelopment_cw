<%-- 
    Document   : makeClaim
    Created on : 16-Nov-2017, 10:46:06
    Author     : Charlie

This page provides a form the user to submit a claim.
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
        <title>Make Claim</title>
    </head>
    <body>
        <form method="post" action="MemberDashboard">
            <input type = "text"
                   id = "raitonale"
                   value = "" 
                   name = "rationale"
                   required
                   placeholder="Enter rationale"
                   /><br>
            <input type = "text"
                   id = "amount"
                   value = "" 
                   name = "amount"
                   required
                   placeholder="Enter amount"
                   /><br>
            
            <input type="submit" name="type" value="Submit Claim"> 
        </form>
        <form method="post" action="MemberDashboard">
            <input type="submit" value="Return">
        </form>
    </body>
</html>
