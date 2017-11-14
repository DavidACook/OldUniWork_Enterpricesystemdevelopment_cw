<%-- 
    Document   : adminDashboardMembers.jsp
    Created on : 13-Nov-2017, 14:47:07
    Author     : Colin Berry
    Desc       : Lists all members, inherits adminDashboard.jsp
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.xyzdrivers.models.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="adminDashboard.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="styles/table.css" />
<link rel="stylesheet" type="text/css" href="styles/main.css" />
<script type="text/javascript" src="javascript/jquery-3.2.1.js"></script>
<script type="text/javascript" src="javascript/clickableTable.js"></script>
<!DOCTYPE html>
<html>
    <body>
        <form id="form" onsubmit="submitRow()" action="AdminDashboard/MemberEdit">
            <input type="submit" value="Edit Member"/>
            <table id="table">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Date of Birth</th>
                    <th>Date of Registration</th>
                    <th>Status</th>
                    <th>Balance</th>
                </tr>
                <c:forEach items="${membersList}" var="member">
                <tr>
                    <td><c:out value="${member.id}"/></td>
                    <td><c:out value="${member.name}"/></td>
                    <td><c:out value="${member.address}"/></td>
                    <td><c:out value="${member.dob}"/></td>
                    <td><c:out value="${member.dor}"/></td>
                    <td><c:out value="${member.status}"/></td>
                    <td><c:out value="${member.balance}"/></td>
                </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>