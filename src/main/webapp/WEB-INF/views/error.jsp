<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link rel="stylesheet" href="<c:url value="/css/estilos.css"/>">
</head>
<body>
    <h1>Ha ocurrido un error</h1>
    <p>Lo sentimos, algo salió mal al procesar tu solicitud.</p>
    <a href="<c:url value="/productos"/>">Volver al inventario</a>
</body>
</html>