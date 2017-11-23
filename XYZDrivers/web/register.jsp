<%-- 
    Document   : register
    Created on : 06-Nov-2017, 11:27:11
    Author     : Colin Berry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="styles/centered.css" />
<link rel="stylesheet" type="text/css" href="styles/main.css" />
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/logo/favicon.ico" />
<!DOCTYPE html>
<html>
    <link href="styles/main.css" rel="stylesheet"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    <h1>Register</h1>
    </head>
     
    <body>
        
            
        <form action="Register" method="post">
        <p>
        <h3>Name:</h3>
            <input type = "text"
                   id = "nameText"
                   value = "" 
                   name = "fname"
                   required
                   placeholder="First Name"
                   /><br>
            <input type = "text"
                   id = "nameText2"
                   value = "" 
                   name = "lname"
                   required
                   placeholder="Last Name"
                   />
        </p>
        <p>
        <h3>Address:</h3>
         
            <input type="text" id="addr1Text" name="addr1" required placeholder="Street"/><br>            
            <input type="text" id="addr2Text" name="addr2" placeholder="Town"/><br>
            <input type="text" id="addr3Text" name="addr3" required placeholder="City"/><br>
            <input type="text" id="addr4Text" name="addr4" required placeholder="Post Code"/><br>
            
        </p>
        <p>
            <h3>D.O.B:</h3>
            <input type="date" id="dob" value="dd/mm/yyyy" name="dob" required >
        </p>
        
    </p>
    <p align="right">
        <input type ="submit" 
               id ="registerButton"
               value ="Register"
               name="register"
               title="Register"/>
    </p>

    
    </form>
</body>
</html>
