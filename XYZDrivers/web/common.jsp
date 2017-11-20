<%-- 
    Document   : common.jsp
    Created on : 14-Nov-2017, 09:49:14
    Author     : Colin Berry
    Desc       : Include on page to add default header and footer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/logo/favicon.ico" />

<t:wrapper>
    <jsp:attribute name="footer"></jsp:attribute>
    <jsp:attribute name="header">
        <form action="${pageContext.request.contextPath}/Index"  align="left">
            <input type="hidden" name="logout" value="true"/>
            <input type="submit" value="Logout"/> <label>${pageTitle}</labe>
        </form>
    </jsp:attribute>
</t:wrapper>