<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.5.7/jquery.fancybox.css" integrity="sha512-nNlU0WK2QfKsuEmdcTwkeh+lhGs6uyOxuUs+n+0oXSYDok5qy0EI0lt01ZynHq6+p/tbgpZ7P+yUb+r71wqdXg==" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.5.7/jquery.fancybox.min.css" integrity="sha512-H9jrZiiopUdsLpg94A333EfumgUBpO9MdbxStdeITo+KEIMaNfHNvwyjjDJb+ERPaRS6DpyRlKbvPUasNItRyw==" crossorigin="anonymous" />
    <link rel="stylesheet" href="css/main.css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="js/moment.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.5.7/jquery.fancybox.min.js" integrity="sha512-uURl+ZXMBrF4AwGaWmEetzrd+J5/8NRkWAvJx5sbPSSuOb0bZLqf+tOzniObO00BjHa/dD7gub9oCGMLPQHtQA==" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.5.7/jquery.fancybox.js" integrity="sha512-j7/1CJweOskkQiS5RD9W8zhEG9D9vpgByNGxPIqkO5KrXrwyDAroM9aQ9w8J7oRqwxGyz429hPVk/zR6IOMtSA==" crossorigin="anonymous"></script>
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
<h4><c:out value="${ad.auto.make.name} ${ad.auto.model.name}, ${ad.auto.manufactured}"/></h4>
<c:if test="${ad.folder != null}">
    <c:forEach items="${ad.folder.photos}" var="photo">
        <a href="<c:url value="/download?folder=${ad.folder.name}&name=${photo.name}"/>" data-fancybox="gallery">
            <img alt="car photo" src="<c:url value="/download?folder=${ad.folder.name}&name=${photo.name}"/>" class="img-responsive img-thumbnail my-thumbnail">
        </a>
    </c:forEach>
</c:if>
<div class="row">
    <div class="col-sm-6 col-xs-12">
        <div>Марка: <c:out value="${ad.auto.make.name}"/></div>
        <div>Модель: <c:out value="${ad.auto.model.name}"/></div>
        <div>Год выпуска: <c:out value="${ad.auto.manufactured}"/></div>
        <div>Модификация: <c:out value="${ad.auto.modification}"/></div>
        <div>Пробег: <fmt:formatNumber type = "number" value = "${ad.auto.mileage}"/> км</div>
        <div>Состояние: <c:if test="${not ad.auto.isBroken}">не</c:if>битый</div>
        <div>Владельцев по ПТС: <c:out value="${ad.auto.ownerNumber}"/></div>
    </div>
    <div class="col-sm-6 col-xs-12">
        <div>Тип кузова: <c:out value="${ad.auto.bodyStyle.name}"/></div>
        <div>Количество дверей: <c:out value="${ad.auto.doors}"/></div>
        <div>Тип двигателя: <c:out value="${ad.auto.engine.name}"/></div>
        <div>Коробка передач: <c:out value="${ad.auto.transmission.name}"/></div>
        <div>Привод: <c:out value="${ad.auto.driveUnit.name}"/></div>
        <div>Руль:
            <c:choose>
                <c:when test="${ad.auto.leftWheel}">
                    левый
                </c:when>
                <c:otherwise>
                    правый
                </c:otherwise>
            </c:choose>
        </div>
        <div>Цвет: <c:out value="${ad.auto.color}"/></div>
    </div>
</div>
<div class="row">
    <div class="col-sm-6 col-xs-12">
        <strong>Стоимость: <fmt:formatNumber type = "number" value = "${ad.price}"/> руб.</strong>
    </div>
    <div class="col-sm-6 col-xs-12">
        <strong>Статус:
        <c:choose>
            <c:when test="${ad.isSold}">
                Продано
            </c:when>
            <c:otherwise>
                В продаже
            </c:otherwise>
        </c:choose>
        </strong>
    </div>
</div>
<h4 style="margin-top: 10px">Информация о продавце:</h4>
<div>Имя: <c:out value="${ad.seller.name}"/></div>
<div>Адрес: <c:out value="${ad.seller.address}"/></div>
<div>Телефон: <c:out value="${ad.seller.phone}"/></div>
<div>
    <c:if test="${ad.seller.id == current.id && !ad.isSold}">
        <a href="<c:url value="/update.do?ad=${ad.id}"/>" class="btn btn-outline-info">Редактировать</a>
    </c:if>
    <a href="<c:url value="/all"/>" class="btn btn-outline-primary">На главную</a>
</div>
</body>
</html>