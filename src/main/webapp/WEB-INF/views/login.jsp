<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" href="<c:url value="/css/estilos.css"/>">
</head>
<body>
    <div class="login-container">
        <h1>Iniciar Sesión</h1>

        <form method="post" action="<c:url value="/login"/>">
            <label>Usuario:
                <input type="text" name="username" required autofocus>
            </label>
            <label>Contraseña:
                <input type="password" name="password" required>
            </label>
            <c:if test="${not empty errorLogin}">
                <p class="alert-error">${errorLogin}</p>
            </c:if>
            <button type="submit">Ingresar</button>
        </form>
    </div>
</body>
</html>