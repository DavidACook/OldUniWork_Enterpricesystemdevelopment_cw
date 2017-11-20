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
            <div class="child">
                <form action="View">
                    <input type="hidden" name="type" value="turnover"/>
                    <button name="filter" class="fillbutton" value="dates">Turnover Between Dates</button>
                    <div>
                        <input class="fillinput" name="date1" type="date" value="2015-01-01"/>
                        <input class="fillinput" name="date2" type="date" value="2016-01-01"/>
                    </div>
                </form>
                <form action="View">
                    <input type="hidden" name="type" value="payments"/>
                    <button name="filter" class="fillbutton" value="dates">Filter Between Dates</button>
                    <div>
                        <input class="fillinput" name="date1" type="date" value="2015-01-01"/>
                        <input class="fillinput" name="date2" type="date" value="2016-01-01"/>
                    </div>
                </form>
                <form action="View">
                    <input type="hidden" name="type" value="payments"/>
                    <button name="filter" class="fillbutton" value="type">Filter by Type Of Payment</button>
                    <select class="fillselect" name="paymentType">
                        <option value="FEE">Fee</option>
                        <option value="CLAIM">Claim</option>
                    </select>
                </form>
                <form action="View">
                    <input type="hidden" name="type" value="payments"/>
                    <button name="filter" class="fillbutton" value="member">Filter by Member ID</button>
                    <div>
                    <input class="fillinput" type="text" name="id" placeholder="a-member"/>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>