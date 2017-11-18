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
                    <tr>
                        <th>ID</th>
                        <th>Member ID</th>
                        <th>Type of Payment</th>
                        <th>Amount</th>
                        <th>Date</th>
                        <th>Time</th>
                    </tr>
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
                </table>
            </div>
        </div>
    </body>
</html>