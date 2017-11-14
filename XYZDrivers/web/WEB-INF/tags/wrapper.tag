<%-- 
    Document   : wrapper.tag
    Created on : 14-Nov-2017, 09:52:03
    Author     : Colin Berry
--%>

<%@tag description="Wrapper Tag" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
  <body>
    <div class="header">
      <jsp:invoke fragment="header"/>
    </div>
    <div class="footer">
      <jsp:invoke fragment="footer"/>
    </div>
  </body>
</html>