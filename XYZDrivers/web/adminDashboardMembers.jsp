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
            <form id="form" onsubmit="submitRow()" action="MemberEdit">
                <table id="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Address</th>
                            <th>Date of Birth</th>
                            <th>Date of Registration</th>
                            <th>Status</th>
                            <th>Balance</th>
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
            <input type="submit" class="fillbutton" value="Edit Member"/>
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