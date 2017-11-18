<%-- 
    Document   : adminDashboardClaims.jsp
    Created on : 13-Nov-2017, 14:55:25
    Author     : Colin Berry
    Desc       : Lists all claims, inherits adminDashboard.jsp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="adminDashboard.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <body>
        <div class="parent">
            <div class="childtable">
                <form id="form" onsubmit="submitRow()" action="ClaimEdit">
                    <table id="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Member ID</th>
                                <th>Date</th>
                                <th>Rationale</th>
                                <th>Status</th>
                                <th>Amount</th>
                            </tr>
                        </thead>
                        <tbody>
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
                        </tbody>
                    </table>
            </div>
            <div>
                <input type="submit" class="fillbutton" value="Edit Claim"/>
            </form>
                <form action="View">
                    <input type="hidden" name="type" value="claims"/>
                    <button name="filter" class="fillbutton" value="status">Filter by Status</button>
                    <select name="status" class="fillselect">
                        <option name="approved">Approved</option>
                        <option name="applied">Applied</option>
                        <option name="rejected">Rejected</option>
                    </select>
                </form>
            </div>
        </div>
    </body>
</html>