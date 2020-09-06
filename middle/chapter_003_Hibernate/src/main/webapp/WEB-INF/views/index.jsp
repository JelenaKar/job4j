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
    <title>To do list</title>
</head>
<body>
<div class="text-right">
    <div><c:out value="${current.name}"/></div>
    <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Выйти</a>
</div>
<h5>Новое задание</h5>
<form id="task-form" class="form-inline" method="post" action="">
    <div class="form-group">
        <label for="descr">Описание задания:</label>
        <input type="text" class="form-control" id="descr" name="descr" placeholder="Введите новое задание">
    </div>
    <div class="form-group">
        <input type="hidden" name="op" value="add">
        <button type="submit" class="btn btn-success">Добавить</button>
    </div>
</form>
<h6 class="text-center">Список заданий</h6>
<table id="task-table" class="table table-bordered">
    <thead>
    <tr>
        <th>id задания</th>
        <th>Описание</th>
        <th>Автор</th>
        <th>Дата создания</th>
        <th>Статус выполнения</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${items}" var="item">
        <tr id="${item.id}">
            <td><c:out value="${item.id}"/></td>
            <td><c:out value="${item.description}"/></td>
            <td><c:out value="${item.user.name}"/></td>
            <jsp:useBean id="dateValue" class="java.util.Date"/>
            <jsp:setProperty name="dateValue" property="time" value="${item.created}"/>
            <td><fmt:formatDate value="${dateValue}" pattern="dd.MM.yyyy HH:mm"/></td>
            <td><input type="checkbox" name="status" <c:if test="${item.done}">checked</c:if>></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>