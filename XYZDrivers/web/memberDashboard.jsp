<%-- 
    Document   : memberDashboard
    Created on : 06-Nov-2017, 11:26:26
    Author     : Colin Berry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Dashboard</title>
    </head>
    <body>
<<<<<<< HEAD
        <h1>Oh look a member dashboard!</h1>
<<<<<<< HEAD
=======
        
        
        
        
        
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
        
        
>>>>>>> david
=======
        <h1>Welcome ${memID}</h1>
        <form method="post" action="MemberDashboard">
            <input type="submit" name="type" value="Check Balance">
            <input type="submit" name="type" value="View Claims">
            <input type="submit" name="type" value="View Payments">           
        </form>
        <form method="post" action="MakeClaim">
            <input type="submit" name="type" value="Make Claim">
        </form>
        ${output}
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
>>>>>>> master
    </body>
</html>
