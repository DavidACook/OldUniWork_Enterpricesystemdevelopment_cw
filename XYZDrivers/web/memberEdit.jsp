<%-- 
    Document   : memberEdit
    Created on : 13-Nov-2017, 13:03:11
    Author     : Colin Berry
--%>

<%@page import="com.xyzdrivers.models.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Member</title>
    </head>
    
    <script>
        function onSubmit(){
            alert("Saved Member Data");
        }
    </script>
    
    <body>
        <h1>Editing Member</h1>
        <form onsubmit="onSubmit()" action="MemberEdit" method="POST">
            <% Member member = (Member) request.getAttribute("member"); %>
            <input name="id" type="hidden" value="<%= member.getId() %>"/>
            <table id="member" border="1" cellpadding="5">
                <tr>
                    <th>ID: </th>
                    <td><%= member.getId() %></td>
                </tr>
                <tr>
                    <th>Name: </th>
                    <td>
                        <input name="name" type="text" value="<%= member.getName() %>"/>
                    </td>
                </tr>
                <tr>
                    <th>Address: </th>
                    <td>
                        <input name="address" type="text" value="<%= member.getAddress() %>"/>
                    </td>
                </tr>
                <tr>
                    <th>DOB: </th>
                    <td>
                        <input name="dob" type="date" value="<%= member.getDob()%>"/>
                    </td>
                </tr>
                <tr>
                    <th>DOR: </th>
                    <td>
                        <input name="dor" type="date" value="<%= member.getDor() %>"/>
                    </td>
                </tr>
                <tr>
                    <th>Status: </th>
                    <td>
                        <input name="status" type="text" value="<%= member.getStatus() %>"/>
                    </td>
                </tr>
                <tr>
                    <th>Balance: </th>
                    <td>
                        <input name="balance" type="number" value="<%= member.getBalance() %>"/>
                    </td>
                </tr>
            </table>
            <input type="submit" value="Save"/>
        </form>
        <form action="../AdminDashboard">
            <input type="hidden" name="type" value="View Members"/>
            <input type="submit" value="Back to Admin Dashboard"/>
        </form>
    </body>
</html>