<%-- 
    Document   : login
    Created on : 06-Nov-2017, 11:27:06
    Author     : Colin Berry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.xyzdrivers.models.User"%>
<link href="styles/main.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="styles/centered.css" />
<!DOCTYPE html>


<!-- Testing --> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form method="post" action="Login">
            <input type="text" name="username" placeholder="Username" /> <br>
            <input type="password" name="password" placeholder="Password"/> <br>
            <p>
            <input name="button" type="submit" value="Login" />      
            </p>
        </form>

    </body>
</html>
