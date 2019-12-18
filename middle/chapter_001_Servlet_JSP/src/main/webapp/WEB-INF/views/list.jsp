<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Просмотр всех пользователей</title>
</head>
<body>
    <table border="1px" style="border-collapse: collapse; margin: 5px 0 5px 0">
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}" /></td>
                <td><c:out value="${user.name}" /></td>
                <td><c:out value="${user.login}" /></td>
                <td><c:out value="${user.email}" /></td>
                <jsp:useBean id="dateValue" class="java.util.Date"/>
                <jsp:setProperty name="dateValue" property="time" value="${user.createDate}"/>
                <td><fmt:formatDate value="${dateValue}" pattern="dd.MM.yyyy HH:mm"/></td>
                <td>
                    <form method="post" style="display:inline">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="id" value="${user.id}"/>
                        <button type="submit">Удалить</button>
                    </form>
                    <form method="get" style="display:inline" action="${pageContext.request.contextPath}/edit">
                        <input type="hidden" name="id" value="${user.id}"/>
                        <button type="submit">Редактировать</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form action="${pageContext.request.contextPath}/create">
        <button type="submit">Новый пользователь</button>
    </form>
</body>
</html>