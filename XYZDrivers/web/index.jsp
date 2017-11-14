<%-- 
    Document   : index
    Created on : 14-Nov-2017, 16:33:52
    Author     : Harry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/centered.css" />
<html>
    <link href="styles/main.css" rel="stylesheet"/>
    <head>
        <title>Home</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>     
        <h1>XYZ Drivers</h1>
        <br>
        <form method="get" action="/Login">
            <input name ="button" type="submit" value="Login"/>
        </form>
        <br>              
        <form method="get" action="/Register">
         <input name ="button" type="submit" value="Register"/>   
        </form>          
    </body>
</html>
