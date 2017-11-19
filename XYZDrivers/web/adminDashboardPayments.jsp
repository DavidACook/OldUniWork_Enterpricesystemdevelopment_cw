<%-- 
    Document   : adminDashboardMembers.jsp
    Created on : 13-Nov-2017, 14:47:07
    Author     : Colin Berry
    Desc       : Lists all members, inherits adminDashboard.jsp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="adminDashboard.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <body>
        <div class="parent">
            <div class="childtable">
                <table id="table">
                    <thead>
                        <tr>
                            <th id="header0" onclick="sortTable(0)">ID</th>
                            <th id="header1" onclick="sortTable(1)">Member ID</th>
                            <th id="header2" onclick="sortTable(2)">Type of Payment</th>
                            <th id="header3" onclick="sortTable(3)">Amount</th>
                            <th id="header4" onclick="sortTable(4)">Date</th>
                            <th id="header5" onclick="sortTable(5)">Time</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${paymentsList}" var="payment">
                        <tr>
                            <td><c:out value="${payment.id}"/></td>
                            <td><c:out value="${payment.mem_id}"/></td>
                            <td><c:out value="${payment.type_of_payment}"/></td>
                            <td><c:out value="${payment.amount}"/></td>
                            <td><c:out value="${payment.date}"/></td>
                            <td><c:out value="${payment.time}"/></td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>