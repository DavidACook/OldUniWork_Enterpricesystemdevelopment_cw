<%-- 
    Document   : common.jsp
    Created on : 14-Nov-2017, 09:49:14
    Author     : Colin Berry
    Desc       : Include on page to add default header and footer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
    <jsp:attribute name="header"></jsp:attribute>
    <jsp:attribute name="footer">
        <form action="index.html">
            <input type="submit" value="Return to Homepage"/>
        </form>
    </jsp:attribute>
</t:wrapper>