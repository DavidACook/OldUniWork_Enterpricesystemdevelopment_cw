<%-- 
    Document   : adminDashboardClaims.jsp
    Created on : 13-Nov-2017, 14:55:25
    Author     : Colin Berry
    Desc       : Lists all claims, inherits adminDashboard.jsp
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.xyzdrivers.models.Claim"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="adminDashboard.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <body>
        <form id="form" onsubmit="submitRow()" action="ClaimEdit">
            <input type="submit" value="Edit Claim"/>
            <table id="table">
                <tr>
                    <th>ID</th>
                    <th>Member ID</th>
                    <th>Date</th>
                    <th>Rationale</th>
                    <th>Status</th>
                    <th>Amount</th>
                </tr>
                <c:forEach items="${claimsList}" var="claim">
                <tr>
                    <td><c:out value="${claim.id}"/></td>
                    <td><c:out value="${claim.mem_id}"/></td>
                    <td><c:out value="${claim.date}"/></td>
                    <td><c:out value="${claim.rationale}"/></td>
                    <td><c:out value="${claim.status}"/></td>
                    <td><c:out value="${claim.amount}"/></td>
                </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>