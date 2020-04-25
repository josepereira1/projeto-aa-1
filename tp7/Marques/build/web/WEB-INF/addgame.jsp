<%-- 
    Document   : addgame
    Created on : 24/abr/2020, 22:42:37
    Author     : joaomarques
--%>

<%@page import="java.util.List"%>
<%@page import="business.Platform"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add Game</h1>
        <form method="POST">
            <label>Name:</label>
            <input type="text" name="name"/>
            <p>
            <label>Year:</label>
            <input type="number" name="year"/>
            <p>
            <label>Price:</label>
            <input type="text" name="price"/>
            <p>
            <label>Platform:</label>
            <select name="platform">
                <% List<Platform> platforms = (List) request.getAttribute("platforms");
                for(Platform p : platforms) { %>
                    <option value="<%= p.getName() %>"><%= p.getName() %></option>
                <%}%>
            </select>
            <p>
            <label>Description:</label>
            <input type="text" name="description"/>
            <p>
            <input type="submit" value="addgame"/>
        </form>
    </body>
</html>
