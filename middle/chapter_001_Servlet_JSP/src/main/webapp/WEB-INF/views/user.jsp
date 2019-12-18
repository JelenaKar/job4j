<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
</head>
<body>
    <form method="post">
        <input type="text" name="name" value="<c:if test="${user != null}"><c:out value="${user.name}"/></c:if>" placeholder="Имя пользователя"/>
        <input type="text" name="login" value="<c:if test="${user != null}"><c:out value="${user.login}"/></c:if>" placeholder="Логин"/>
        <input type="text" name="email" value="<c:if test="${user != null}"><c:out value="${user.email}"/></c:if>" placeholder="E-mail"/>
        <button type="submit">${button}</button>
    </form>
<div>${status}</div>
</body>
</html>