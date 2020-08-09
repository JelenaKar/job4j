<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="ru">
<head>
    <title>Завершение покупки</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="js/payment.js"></script>
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <h1><c:out value = "${success}"/></h1>
        <c:choose>
            <c:when test="${error == null}">
                <p>Вы успешно приобрели билет:</p>
                <p>Ряд: ${chosen.row} место ${chosen.col}</p>
                <p>По цене : <fmt:formatNumber type = "number" value = "${chosen.price}" /> рублей.</p>
            </c:when>
            <c:otherwise>
                <p>Возможные причины:</p>
                <ul>
                    <li>Место уже занято</li>
                    <li>Введены неверные данные аккаунта</li>
                    <li>На счёте недостаточно средств для совершения покупки</li>
                </ul>
            </c:otherwise>
        </c:choose>
        <a href="${pageContext.servletContext.contextPath}/index.html" class="btn btn-success">К началу</a>
    </div>
</div>
</body>
</html>
