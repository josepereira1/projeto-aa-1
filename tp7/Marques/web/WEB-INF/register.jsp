<%-- 
    Document   : register
    Created on : 24/abr/2020, 22:13:27
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
        <h1>Register</h1>
        <form method="POST">
            <p>
            <label>Name:</label>
            <input type="text" name="name"/>
            <p>
            <label>Email:</label>
            <input type="text" name="email"/>
            <p>
            <label>Password:</label>
            <input type="password" name="password"/>
            <p>
            <input type="submit" value="register"/>
        </form>
    </body>
</html>
