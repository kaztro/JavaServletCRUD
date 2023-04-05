<%-- 
    Document   : lista-estudiantes
    Created on : 5 abr. 2023, 16:47:05
    Author     : germa
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de estudiantes</title>
    </head>
    <body>
        <h1>Lista de estudiantes</h1>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="estudiante" items="${listaUsuarios}">
                    <tr>
                        <td><c:out value="${estudiante.id}" /></td>
                        <td><c:out value="${estudiante.nombre}" /></td>
                        <td><c:out value="${estudiante.apellido}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
