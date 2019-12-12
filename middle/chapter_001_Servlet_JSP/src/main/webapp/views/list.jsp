<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.crud.User" %>
<%@ page import="java.text.DateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Просмотр всех пользователей</title>
</head>
<body>
    <table border="1px" style="border-collapse: collapse; margin: 5px 0 5px 0">
    <%
        List<User> users = (List<User>) request.getAttribute("users");
        DateFormat format = (DateFormat) request.getAttribute("format");
        for (User user : users) {
    %>
        <tr>
            <td><%=user.getId()%></td>
            <td><%=user.getName()%></td>
            <td><%=user.getLogin()%></td>
            <td><%=user.getEmail()%></td>
            <td><%=format.format(user.getCreateDate())%></td>
            <td>
                <form method="post" style="display:inline">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="id" value="<%=user.getId()%>"/>
                    <button type="submit">Удалить</button>
                </form>
                <form method="get" style="display:inline" action="<%=request.getContextPath()%>/edit">
                    <input type="hidden" name="id" value="<%=user.getId()%>"/>
                    <button type="submit">Редактировать</button>
                </form>
            </td>
        </tr>
    <% } %>
    </table>
    <form action="<%=request.getContextPath()%>/create">
        <button type="submit">Новый пользователь</button>
    </form>
</body>
</html>