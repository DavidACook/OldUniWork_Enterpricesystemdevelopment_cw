<%-- 
    Document   : register
    Created on : 06-Nov-2017, 11:27:11
    Author     : Colin Berry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h1>Oh look a denk register page!</h1>

        

        <a id="Navigate" href="./index.html">
            <input 

                type="button"
                id="homeButton"
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
        <form action="Register" method="post">
        <p>
            <label>Name:</label>
            <input type = "text"
                   id = "nameText"
                   value = "" 
                   name = "name"/>
        </p>
        <p>
            <label>Address:</label>
            <textarea id="addrText" name="address"/>
            
            </textarea> 
        </p>
        <p>
            <label>D.O.B:</label>
            <input type="date" id="dob" value="dd/mm/yyyy" name="dob" >
        </p>
        
    </p>

    <p>
        <input type ="submit"
               id ="registerButton"
               value ="Register"
               name="register"

    </p>
    </form>
    <p>Today's date: <%= (new java.util.Date()).toLocaleString()%></p>    
</body>
</html>
