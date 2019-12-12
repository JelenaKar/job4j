<%@ page import="ru.job4j.crud.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title><%=request.getAttribute("title")%></title>
</head>
<body>
    <%
        User user = (User) request.getAttribute("user");
    %>
    <form method="post">
        <input type="text" name="name" value="<%if (user != null) out.print(user.getName());%>" placeholder="Имя пользователя"/>
        <input type="text" name="login" value="<%if (user != null) out.print(user.getLogin());%>" placeholder="Логин"/>
        <input type="text" name="email" value="<%if (user != null) out.print(user.getEmail());%>" placeholder="E-mail"/>
        <button type="submit"><%=request.getAttribute("button")%></button>
    </form>
    <div><%=request.getAttribute("status")%></div>
</body>
</html>