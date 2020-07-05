<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>${title}</title>
</head>
<body style="margin: 8px;">
<div class="w3-bar">
    <c:choose>
        <c:when test="${current != null}">
            <a href="#" class="w3-bar-item w3-button w3-red w3-right">Выйти</a>
            <span class="w3-bar-item w3-right"><c:out value="${current.name}"/></span>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.servletContext.contextPath}/login" class="w3-bar-item w3-button w3-green w3-right">Войти</a>
        </c:otherwise>
    </c:choose>
</div>
    <form id="userForm" method="post" enctype="multipart/form-data">
        <input type="text" name="name" value="<c:if test="${user != null}"><c:out value="${user.name}"/></c:if>" placeholder="Имя пользователя" required/>
        <input type="text" name="login" value="<c:if test="${user != null}"><c:out value="${user.login}"/></c:if>" placeholder="Логин" required/>
        <input type="email" name="email" value="<c:if test="${user != null}"><c:out value="${user.email}"/></c:if>" placeholder="E-mail" required/>
        <input type="hidden" name="photoid" value="<c:if test="${user != null}"><c:out value="${user.photoid}"/></c:if>"/>
        <button type="submit" name="action" value="${action}">${button}</button>
    </form>
    <div style="color: crimson">${status}</div>
    <h3>Фото профиля</h3>
    <c:choose>
        <c:when test="${user != null && user.photoid != null}">
            <img src="${pageContext.servletContext.contextPath}/download?name=${user.photoid}" width="100px" alt="User's photo">
            <button form="userForm" type="submit" name="action" value="removePhoto" class="w3-btn w3-red">Удалить фото</button>
            <h5>Изменить изображение</h5>
        </c:when>
        <c:otherwise>
            <div>Фото отсутствует</div>
            <h5>Загрузка изображения</h5>
        </c:otherwise>
    </c:choose>
    <div><input form="userForm" type="file" name="file"></div>
</body>
</html>