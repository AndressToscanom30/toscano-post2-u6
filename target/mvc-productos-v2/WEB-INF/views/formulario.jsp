<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'es'}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="form.titulo.${empty producto ? 'nuevo' : 'editar'}"/></title>
    <link rel="stylesheet" href="<c:url value="/css/estilos.css"/>">
</head>
<body class="form-page">
    <h1><fmt:message key="form.titulo.${empty producto ? 'nuevo' : 'editar'}"/></h1>

    <%-- Lista de errores de validación --%>
    <c:if test="${not empty errores}">
        <div class="alert-error">
            <ul>
                <c:forEach var="e" items="${errores}">
                    <li>${e.value}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>

    <form method="post" action="<c:url value="/productos"/>">

        <c:if test="${not empty producto}">
            <input type="hidden" name="id"     value="${producto.id}">
            <input type="hidden" name="accion" value="actualizar">
        </c:if>
        <c:if test="${empty producto}">
            <input type="hidden" name="accion" value="guardar">
        </c:if>

        <%-- Campo Nombre con retroalimentación de error --%>
        <label><fmt:message key="form.nombre"/>
            <input type="text" name="nombre"
                   value="<c:out value="${not empty nombre ? nombre : producto.nombre}"/>"
                   class="${not empty errores.nombre ? 'input-error' : ''}">
            <c:if test="${not empty errores.nombre}">
                <span class="campo-error">${errores.nombre}</span>
            </c:if>
        </label>

        <%-- Campo Categoría --%>
        <label><fmt:message key="form.categoria"/>
            <input type="text" name="categoria"
                   value="<c:out value="${not empty categoria ? categoria : producto.categoria}"/>">
        </label>

        <%-- Campo Precio con retroalimentación de error --%>
        <label><fmt:message key="form.precio"/>
            <input type="number" name="precio" step="0.01" min="0"
                   value="${not empty precio ? precio : producto.precio}"
                   class="${not empty errores.precio ? 'input-error' : ''}">
            <c:if test="${not empty errores.precio}">
                <span class="campo-error">${errores.precio}</span>
            </c:if>
        </label>

        <%-- Campo Stock con retroalimentación de error --%>
        <label><fmt:message key="form.stock"/>
            <input type="number" name="stock" min="0"
                   value="${not empty stock ? stock : producto.stock}"
                   class="${not empty errores.stock ? 'input-error' : ''}">
            <c:if test="${not empty errores.stock}">
                <span class="campo-error">${errores.stock}</span>
            </c:if>
        </label>

        <button type="submit">
            <fmt:message key="form.${empty producto ? 'guardar' : 'actualizar'}"/>
        </button>
        <a href="<c:url value="/productos"/>"><fmt:message key="form.cancelar"/></a>
    </form>
</body>
</html>