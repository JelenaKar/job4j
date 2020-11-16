<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="css/main.css" >
    <script src="https://code.jquery.com/jquery-3.2.1.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="js/moment.js"></script>
    <script src="js/main.js"></script>
    <title>Продажа автомобилей</title>
</head>
<body>
<div class="text-right">
    <c:choose>
        <c:when test="${current != null}">
            <span><c:out value="${current.name}"/></span>
            <a href="<c:url value="/out"/>" class="btn btn-dark">Выйти</a>
        </c:when>
        <c:otherwise>
            <a href="<c:url value="/in"/>" class="btn btn-success">Войти</a>
        </c:otherwise>
    </c:choose>
</div>
<h5>Список всех объявлений</h5>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>Марка</th>
        <th>Год выпуска</th>
        <th>Тип кузова</th>
        <th>Тип двигателя</th>
        <th>Привод</th>
        <th>Коробка передач</th>
        <th>Стоимость</th>
        <th>Опубликовано</th>
        <th>Статус</th>
        <th>Действия</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ads}" var="ad">
        <tr id="${ad.id}" data-seller="${ad.seller.id}">
            <td><c:out value="${ad.auto.make.name} ${ad.auto.model.name}"/></td>
            <td><c:out value="${ad.auto.manufactured}"/></td>
            <td><c:out value="${ad.auto.bodyStyle.name}"/></td>
            <td><c:out value="${ad.auto.engine.name}"/></td>
            <td><c:out value="${ad.auto.driveUnit.name}"/></td>
            <td><c:out value="${ad.auto.transmission.name}"/></td>
            <td><fmt:formatNumber type = "number" value = "${ad.price}"/> руб.</td>
            <jsp:useBean id="dateValue" class="java.util.Date"/>
            <jsp:setProperty name="dateValue" property="time" value="${ad.created}"/>
            <td><fmt:formatDate value="${dateValue}" pattern="dd.MM.yyyy HH:mm"/></td>
            <td>
                <c:choose>
                    <c:when test="${ad.isSold}">
                        Продано
                    </c:when>
                    <c:otherwise>
                        В продаже
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="<c:url value="/view?ad=${ad.id}"/>" class="btn btn-info">Подробнее</a>
                <c:if test="${ad.seller.id == current.id && !ad.isSold}">
                    <a href="<c:url value="/update.do?ad=${ad.id}"/>" class="btn btn-secondary">Редактировать</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${current != null}">
    <div class="text-right">
        <a href="<c:url value="/create.do"/>" class="btn btn-success">Новое объявление</a>
    </div>
</c:if>
</body>
</html>