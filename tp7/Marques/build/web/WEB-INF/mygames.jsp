<%-- 
    Document   : mygames
    Created on : 24/abr/2020, 22:31:54
    Author     : joaomarques
--%>

<%@page import="java.util.List"%>
<%@page import="business.Game"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Game Library</title>
    </head>
    <body>
        <h1>My Games</h1>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Year</th>
                    <th>Price</th>
                    <th>Platform</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody>
                <% List<Game> games = (List) request.getAttribute("games");
                for(Game g : games) { %>
                    <tr>
                        <td><%= g.getName() %></td>
                        <td><%= g.getYear() %></td>
                        <td><%= g.getPrice() %></td>
                        <td><%= g.getPlatform().getName() %></td>
                        <td><%= g.getDescription() %></td>
                    </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
