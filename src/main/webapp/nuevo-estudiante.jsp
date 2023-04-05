<%-- 
    Document   : nuevo-estudiante
    Created on : 5 abr. 2023, 16:46:42
    Author     : germa
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Agregar un nuevo estudiante</h1>
        <form action="insert" method="POST">
            <label>Nombre</label>
            <input type="text" value="<c:out value='${estudiante.nombre}'/> name="nombre" required="required" ">
            <label>Apellido</label>
            <input type="text" value="<c:out value='${estudiante.apellido}'/> name="apellido" required="required" ">
            
            <button type="submit">Guardar</button>
        </form>
    </body>
</html>
