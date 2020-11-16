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
    <script src="js/handling.js"></script>
    <title>Продажа автомобилей</title>
</head>
<body>
<div class="text-right">
    <span><c:out value="${current.name}"/></span>
    <a href="<c:url value="/out"/>" class="btn btn-dark">Выйти</a>
</div>
<h5><c:out value="${title}"/></h5>
<form method="post" id="ad-form">
    <div class="form-group">
        <select id="make" name="make_select">
            <option value="">—</option>
            <c:forEach items="${makes}" var="make">
                <option value="${make.id}"<c:if test="${make.id == ad.auto.make.id}">selected</c:if>><c:out value="${make.name}"/></option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <select id="model" name="model_select">
            <option value="">—</option>
            <c:forEach items="${models}" var="model">
                <option value="${model.id}"<c:if test="${model.id == ad.auto.model.id}">selected</c:if>><c:out value="${model.name}"/></option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label>Год выпуска</label>
        <input type="text" name="manufactured" value="${ad.auto.manufactured}" required/>
    </div>
    <div class="form-group">
        <c:forEach items="${bodyStyles}" var="style">
            <input type="radio" name="body_style" value="${style.id}"
                   <c:if test="${style.id == ad.auto.bodyStyle.id}">checked</c:if>
            /><c:out value="${style.name}"/>&nbsp;
        </c:forEach>
    </div>
    <div class="form-group">
        <label>Количество дверей</label>
        <input type="text" name="doors" value="${ad.auto.doors}" required/>
    </div>
    <div class="form-group">
        <label>Тип двигателя</label><br/>
        <c:forEach items="${engineTypes}" var="engine">
            <input type="radio" name="engine_type" value="${engine.id}"
                   <c:if test="${engine.id == ad.auto.engine.id}">checked</c:if>
            /><c:out value="${engine.name}"/>&nbsp;
        </c:forEach>
    </div>
    <div class="form-group">
        <label>Привод</label><br/>
        <c:forEach items="${driveUnits}" var="unit">
            <input type="radio" name="drive_unit" value="${unit.id}"
                   <c:if test="${unit.id == ad.auto.driveUnit.id}">checked</c:if>
            /><c:out value="${unit.name}"/>&nbsp;
        </c:forEach>
    </div>
    <div class="form-group">
        <label>Коробка передач</label><br/>
        <c:forEach items="${transmissions}" var="transmission">
            <input type="radio" name="transmission" value="${transmission.id}"
                   <c:if test="${transmission.id == ad.auto.transmission.id}">checked</c:if>
            /><c:out value="${transmission.name}"/>&nbsp;
        </c:forEach>
    </div>
    <div class="form-group">
        <label>Модификация</label>
        <input type="text" name="modification" value="${ad.auto.modification}" required/>
    </div>
    <div class="form-group">
        <label>Цвет</label>
        <input type="text" name="color" value="${ad.auto.color}" required/>
    </div>
    <div class="form-group">
        <label>Руль</label><br/>
        <input type="radio" name="wheel" value="true"
               <c:if test="${ad.auto.leftWheel or ad == null}">checked</c:if>
            />Левый&nbsp;
        <input type="radio" name="wheel" value="false"
               <c:if test="${not ad.auto.leftWheel and ad != null}">checked</c:if>
            />Правый&nbsp;
    </div>
    <div class="form-group">
        <label>Пробег</label>
        <input type="text" name="mileage" value="${ad.auto.mileage}" required/>
    </div>
    <div class="form-group">
        <label>Состояние</label><br/>
        <input type="radio" name="is_broken" value="false"
               <c:if test="${not ad.auto.isBroken or ad == null}">checked</c:if>
        />Не битый&nbsp;
        <input type="radio" name="is_broken" value="true"
               <c:if test="${ad.auto.isBroken}">checked</c:if>
        />Битый&nbsp;
    </div>
    <div class="form-group">
        <label>Владельцев по ПТС</label>
        <input type="text" name="owners" value="${ad.auto.ownerNumber}" required/>
    </div>
    <div class="form-group">
        <label>Цена</label>
        <input type="text" name="price" value="${ad.price}" required/> руб.
    </div>
    <div class="my-container">
        <c:if test="${ad.folder != null}">
            <c:forEach items="${ad.folder.photos}" var="photo">
                <div class="text-center photo-container" data-folder="${ad.folder.name}">
                    <img src="<c:url value="/download?folder=${ad.folder.name}&name=${photo.name}"/>" class="img-responsive my-thumbnail">
                    <button id="${photo.id}" class="deleter">Удалить</button>
                </div>
            </c:forEach>
        </c:if>
        <div class="image-upload">
            <label for="file-input">
                <img src="images/add-image.png" data-ad="${ad.id}"/>
            </label>
        </div>
    </div>
    <button type="submit" class="btn btn-success" id="sent-form">Сохранить</button>
</form>
<form id="upload-pic" enctype="multipart/form-data">
    <input id="file-input" type="file" name="file"/>
</form>
<c:if test="${ad != null}">
    <button class="btn btn-danger is-sold" data-ad="${ad.id}">Продано!</button>
</c:if>
</body>
</html>