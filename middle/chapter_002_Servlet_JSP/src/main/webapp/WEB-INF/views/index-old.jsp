<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <title>Сервис кинотеатра</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="css/main.css" >
    <script src="https://code.jquery.com/jquery-3.2.1.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="js/main.js"></script>
</head>
<body>
<div class="container">
    <div class="row pt-3">
        <h4>Бронирование месте на сеанс</h4>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th style="width: 120px;">Ряд / Место</th>
                <c:forEach var = "i" begin = "1" end = "${hall.width}">
                    <th><c:out value = "${i}"/></th>
                </c:forEach>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${hall.matrix}" var="row">
                <tr>
                    <th><c:out value="${row[0].row}"/></th>
                    <c:forEach items="${row}" var="place">
                        <td class="seat r${place.row}c${place.col}">
                            <input type="radio" name="place"> Ряд ${place.row}, Место ${place.col}
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div style="color: #dc143c">Красным выделены занятые места</div>
    <div class="row float-right">
        <button type="button" class="btn btn-success" onclick="doPayment()">Оплатить</button>
    </div>
</div>
</body>
</html>