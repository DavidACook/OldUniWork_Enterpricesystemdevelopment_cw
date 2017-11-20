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
            <form id="form" onsubmit="submitRow()" action="Edit">
                <table id="table">
                    <thead>
                        <tr>
                            <th id="header0" onclick="sortTable(0)">ID</th>
                            <th id="header1" onclick="sortTable(1)">Name</th>
                            <th id="header2" onclick="sortTable(2)">Address</th>
                            <th id="header3" onclick="sortTable(3)">Date of Birth</th>
                            <th id="header4" onclick="sortTable(4)">Date of Registration</th>
                            <th id="header5" onclick="sortTable(5)">Status</th>
                            <th id="header6" onclick="sortTable(6)">Balance</th>
                        </tr>
                    </thead>
                    <tbody>
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
                    </tbody>
                </table>
            </div>
            <div class="child">
                <input type="hidden" name="type" value="member"/>
                <button name="action" class="fillbutton" value="edit">Edit Member</button>
                <div><button name="action" class="fillbutton" value="approve">Approve Member</button></div>
                <button name="action" class="fillbutton" value="reject">Suspend Member</button>
                </form>
                <form action="View">
                    <input type="hidden" name="type" value="members"/>
                    <button name="filter" class="fillbutton" value="outstanding">Filter Outstanding Members</button>
                </form>
                <form action="View">
                    <input type="hidden" name="type" value="members"/>
                    <button name="filter" class="fillbutton" value="status">Filter By Status</button>
                    <select class="fillselect" name="status">
                        <option value="APPROVED">Approved</option>
                        <option value="SUSPENDED">Suspended</option>
                        <option value="APPLIED">Applied</option>
                    </select>
                </form>
            </div>
        </div>
    </body>
</html>