<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js" crossorigin="anonymous"></script>
    <script src="js/main.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Авторизация</title>
    <style>
        .grid-container {
            display: grid;
            grid-template-columns: auto auto auto auto;
            padding: 10px;
        }
    </style>
</head>
<body style="margin: 8px;">
    <div class="grid-container">
        <div class="w3-card-4" style="grid-column: 2 / span 2;">
            <div class="w3-container w3-green">
                <h2>Авторизироваться</h2>
            </div>
            <form class="w3-container" id="login-form" method="post">
                <p>
                    <label>E-mail:</label>
                    <input class="w3-input" name="email" type="email"></p>
                <p>
                    <label>Пароль:</label>
                    <input class="w3-input" name="password" type="password"></p>
                <br>
                <button class="w3-button w3-green" id="submitter">Войти</button>
            </form>
            <div id="error"></div>
        </div>
    </div>
</body>
</html>
