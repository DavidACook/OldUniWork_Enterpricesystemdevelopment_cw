<%-- 
    Document   : adminDashboardTurnover
    Created on : 20-Nov-2017, 14:20:08
    Author     : Colin Berry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="adminDashboard.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Turnover</title>
    </head>
    <body>
        <h1>${param.date1} to ${param.date2}<h1>
        <h1>Revenue (Payments)</h1>
        <h2><c:out value="${revenue}"/></h2>
        <h1>Expenditure (Claims)</h1>
        <h2><c:out value="${expenditure}"/></h2>
        <h1>Turnover</h1>
        <h2><c:out value="${turnover}"/></h2>
    </body>
</html>
