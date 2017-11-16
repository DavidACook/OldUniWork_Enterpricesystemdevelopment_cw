<%-- 
    Document   : makeClaim
    Created on : 16-Nov-2017, 10:46:06
    Author     : Charles
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="common.jsp" %>
<link rel="stylesheet" type="text/css" href="styles/main.css" />
<link rel="stylesheet" type="text/css" href="styles/centered.css" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Make Claim</title>
    </head>
    <body>
        <form method="post" action="MemberDashboard">
            <input type = "text"
                   id = "raitonale"
                   value = "" 
                   name = "rationale"
                   required
                   placeholder="Enter rationale"
                   /><br>
            <input type = "text"
                   id = "amount"
                   value = "" 
                   name = "amount"
                   required
                   placeholder="Enter amount"
                   /><br>
            <input type="submit" name="type" value="Submit Claim">
        </form>
        <p>
            <a id="Navigate" href="./index.html">
                <input type="button" id="homeButton"
                    style="
                    background-image: url(http://cdn3.blogsdna.com/wp-content/uploads/2010/03/Windows-Phone-7-Series-Icons-Pack.png);
                    background-repeat: no-repeat;
                    background-position: -272px -112px;
                    cursor:pointer;
                    height: 40px;
                    width: 40px;
                    border-radius: 26px;
                    border-style: solid;
                    border-color:#000;
                    border-width: 3px;" title="Navigate"
              />
          </a> 
        </p>
    </body>
</html>
