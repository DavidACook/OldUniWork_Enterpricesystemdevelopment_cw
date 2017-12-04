<%-- 
    Document   : adminDashboard
    Created on : 06-Nov-2017, 11:15:59
    Author     : Colin Berry
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="common.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/table.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-3.2.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/clickableTable.js" ></script>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Dashboard</title>
    <h1>Admin Dashboard</h1>
</head>
<body>
    <c:if test="${not empty claimMembers}">
        <p>Charged ${claimMembers} Members ${claimShare} each of total ${claimTotal}</p>
    </c:if>
    <form action="View">
        <button name="type" value="members">View All Members</button>
        <button name="type" value="claims">View All Claims</button>
        <button name="type" value="payments">View All Payments</button>
        <button name="type" value="fees">Apply Annual Fees</button>
    </form>
</body>