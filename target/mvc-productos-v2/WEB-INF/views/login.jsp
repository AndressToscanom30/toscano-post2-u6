<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'es'}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="login.titulo"/></title>
    <link rel="stylesheet" href="<c:url value="/css/estilos.css"/>">
</head>
<body class="login-page">
    <div class="login-container">
        <h1><fmt:message key="login.titulo"/></h1>

        <form method="post" action="<c:url value="/login"/>">
            <label><fmt:message key="login.usuario"/>
                <input type="text" name="username" required autofocus>
            </label>
            <label><fmt:message key="login.password"/>
                <input type="password" name="password" required>
            </label>
            <c:if test="${not empty errorLogin}">
                <p class="alert-error">${errorLogin}</p>
            </c:if>
            <button type="submit"><fmt:message key="login.ingresar"/></button>
        </form>
    </div>
</body>
</html>