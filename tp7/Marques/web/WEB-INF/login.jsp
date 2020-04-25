<%-- 
    Document   : login
    Created on : 24/abr/2020, 22:24:37
    Author     : joaomarques
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Game Library</title>
    </head>
    <body>
        <h1>Login</h1>
        <%
            String error = (String) request.getAttribute("error");
            if(error!=null) out.println(error);
            else out.println("");
            
        %>
        <form method="POST">
            <p>
            <label>Name:</label>
            <input type="text" name="name"/>
            <p>
            <label>Password:</label>
            <input type="password" name="password"/>
            <p>
            <input type="submit" name="action" value="login"/>
            <input type="submit" name="action" value="register"/>
        </form>
    </body>
</html>
