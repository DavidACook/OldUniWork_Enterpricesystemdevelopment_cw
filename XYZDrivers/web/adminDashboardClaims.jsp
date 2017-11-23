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
                <form id="form" onsubmit="submitRow()" action="Edit">
                    <table id="table">
                        <thead>
                            <tr>
                                <th id="header0" onclick="sortTable(0)">ID</th>
                                <th id="header1" onclick="sortTable(1)">Member ID</th>
                                <th id="header2" onclick="sortTable(2)">Date</th>
                                <th id="header3" onclick="sortTable(3)">Rationale</th>
                                <th id="header4" onclick="sortTable(4)">Status</th>
                                <th id="header5" onclick="sortTable(5)">Amount</th>
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
                <input type="hidden" name="type" value="claim"/>
                <button class="fillbutton"/>Edit Claim</button>
                <div><button name="action" class="fillbutton" onclick="return confirm('Approve this claim?')" value="approve"/>Approve Claim</button></div>
                <button name="action" class="fillbutton" onclick="return confirm('Reject this claim?')" value="reject"/>Reject Claim</button>
            </form>
                <form action="View">
                    <input type="hidden" name="type" value="claims"/>
                    <button name="filter" class="fillbutton" value="status">Filter by Status</button>
                    <select name="status" class="fillselect">
                        <option value="APPROVED">Approved</option>
                        <option value="APPLIED">Applied</option>
                        <option value="REJECTED">Rejected</option>
                        <option value="CHARGED">Charged</option>
                    </select>
                </form>
                <form action="View">
                    <input type="hidden" name="type" value="claims"/>
                    <button name="filter" class="fillbutton" value="member">Filter by Member ID</button>
                    <input type="text" class="fillinput" name="id" placeholder="a-member"/>
                </form>
                <form action="View">
                    <input type="hidden" name="type" value="chargeClaims"/>
                    <button class="fillbutton">Annual claim charges</button>
                </form>
            </div>
        </div>
    </body>
</html>