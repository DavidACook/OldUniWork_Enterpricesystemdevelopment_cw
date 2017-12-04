<%-- 
    Document   : memberDashboardClaims
    Created on : 19-Nov-2017, 16:18:03
    Author     : Charlie Arnold

This page displays all the claims submitted by a member in a table format.
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.xyzdrivers.models.Claim"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/table.css" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="memberDashboard.jsp"%>
<!DOCTYPE html>
<html>
    <body>
        <table id="table">
                <tr>
                    <th>Date</th>
                    <th>Rationale</th>
                    <th>Status</th>
                    <th>Amount</th>
                </tr>
                <c:forEach items="${claimsList}" var="claim">
                <tr>
                    <td><c:out value="${claim.date}"/></td>
                    <td><c:out value="${claim.rationale}"/></td>
                    <td><c:out value="${claim.status}"/></td>
                    <td><c:out value="${claim.amount}"/></td>
                </tr>
                </c:forEach>
            </table>
    </body>
</html>
