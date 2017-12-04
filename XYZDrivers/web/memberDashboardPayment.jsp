<%-- 
    Document   : memberDashboardPayment
    Created on : 19-Nov-2017, 16:32:19
    Author     : Charlie

This page displays all the payments made by a member in a table format.
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.xyzdrivers.models.Claim"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/table.css" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="memberDashboard.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <table id="table">
                <tr>
                    <th>Type of payment</th>
                    <th>Amount</th>
                    <th>Date</th>
                    <th>Time</th>
                </tr>
                <c:forEach items="${paymentsList}" var="payment">
                <tr>
                    <td><c:out value="${payment.type_of_payment}"/></td>
                    <td><c:out value="${payment.amount}"/></td>
                    <td><c:out value="${payment.date}"/></td>
                    <td><c:out value="${payment.time}"/></td>
                </tr>
                </c:forEach>
            </table>
    </body>
</html>
