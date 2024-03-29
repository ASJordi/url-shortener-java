<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${title}</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.svg" type="image/svg+xml">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/water.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <main>
        <h1 class="center">${title}</h1>
        <h2>Create a short url</h2>
        <form action="${pageContext.request.contextPath}/urls/shorten" method="post">
            <input type="text" name="link" id="link" class="full-width" placeholder="Shorten your link" value="${url.getUrl()}" required />
            <div>
                <input type="text" name="hash" id="hash" placeholder="Hash to use for the link" value="${url.getHash()}" required/>
                <input type="password" name="auth" id="auth" placeholder="Auth Code" required>
            </div>
            <input type="submit" class="full-width" value="Shorten!">
        </form>
        <c:if test="${errors != null || !errors.isEmpty()}">
            <c:forEach items="${errors.values()}" var="error" >
                <p style="color: #ff0000">${error}</p>
            </c:forEach>
        </c:if>
        <h2 class="center">Already used URLs</h2>

        <c:choose>
            <c:when test="${hashes == null || hashes.isEmpty()}">
                <strong>Nothing here yet!</strong>
            </c:when>
            <c:otherwise>
                <strong>
                <c:forEach items="${hashes}" var="hash">
                    <a href="${pageContext.request.contextPath}/r/${hash}" rel="noreferrer" target="_blank">${hash}</a><br>
                </c:forEach>
                </strong>
            </c:otherwise>
        </c:choose>
    </main>
</body>
</html>