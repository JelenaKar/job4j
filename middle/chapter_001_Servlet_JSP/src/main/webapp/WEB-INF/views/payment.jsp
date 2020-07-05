<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="ru">
<head>
    <title>Сервис кинотеатра</title>
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
    <div class="row pt-3">
        <h3>
            Вы выбрали ряд ${chosen.row} место ${chosen.col},
            Сумма : <fmt:formatNumber type = "number" value = "${chosen.price}" /> рублей.
        </h3>
    </div>
    <div class="row">
        <form method="post">
            <div class="form-group">
                <label for="username">ФИО</label>
                <input type="text" class="form-control" id="username" name="fullname" placeholder="ФИО" required>
            </div>
            <div class="form-group">
                <label for="phone">Номер телефона</label>
                <input type="tel" class="form-control" id="phone" name="phone" placeholder="+79101234567" pattern="\+[0-9]{11,15}" required>
            </div>
            <button type="submit" class="btn btn-success">Оплатить</button>
        </form>
    </div>
</div>
</body>
</html>