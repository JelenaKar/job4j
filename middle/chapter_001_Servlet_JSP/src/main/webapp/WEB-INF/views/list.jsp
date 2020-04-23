<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Просмотр всех пользователей</title>
</head>
<body style="margin: 8px;">
    <table border="1px" style="border-collapse: collapse; margin: 5px 0 5px 0">
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}" /></td>
                <td><c:out value="${user.name}" /></td>
                <td><c:out value="${user.login}" /></td>
                <td><c:out value="${user.email}" /></td>
                <td>
                    <c:if test="${user.photoid != null}">
                        <a href="${pageContext.servletContext.contextPath}/download?name=${user.photoid}" style="display: inline-block">
                            <img src="${pageContext.servletContext.contextPath}/download?name=${user.photoid}" width="100px">
                        </a>
                    </c:if>
                </td>
                <jsp:useBean id="dateValue" class="java.util.Date"/>
                <jsp:setProperty name="dateValue" property="time" value="${user.createDate}"/>
                <td><fmt:formatDate value="${dateValue}" pattern="dd.MM.yyyy HH:mm"/></td>
                <td>
                    <form id="user${user.id}" method="post" style="display:inline">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="id" value="${user.id}"/>
                    </form>
                    <div class="w3-bar">
                        <button form="user${user.id}" class="w3-bar-item w3-button w3-red">Удалить</button>
                        <a href="${pageContext.request.contextPath}/edit?id=${user.id}" class="w3-bar-item w3-button w3-dark-gray">Изменить</a>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/create" class="w3-btn w3-green">Новый пользователь</a>
</body>
</html>