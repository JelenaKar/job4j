package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет, осуществляющий добавление нового пользователя.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public class UserCreateServlet extends HttpServlet {
    private static final ThreadLocal<CrudStatus> STATUS_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<User> USER_THREAD_LOCAL = new ThreadLocal<>();
    private final ActionDispatcher dispatcher = ActionDispatcher.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            String name = "";
            String login = "";
            String email = "";
            User user = USER_THREAD_LOCAL.get();
            if (user != null) {
                name = user.getName();
                login = user.getLogin();
                email = user.getEmail();
            }
            writer.append("<!DOCTYPE html>")
                    .append("<html lang=\"ru\">")
                    .append("<head>")
                        .append("<meta charset=\"UTF-8\">")
                        .append("<title>Создание пользователя</title>")
                    .append("</head>")
                    .append("<body>")
                        .append("<form method=\"post\">")
                            .append("<input type=\"text\" name=\"name\" value=\"" + name + "\" placeholder=\"Имя пользователя\">")
                            .append("<input type=\"text\" name=\"login\" value=\"" + login + "\" placeholder=\"Логин\">")
                            .append("<input type=\"text\" name=\"email\" value=\"" + email + "\" placeholder=\"E-mail\">")
                            .append("<button type=\"submit\">Добавить пользователя</button>")
                        .append("</form>");
                    if (STATUS_THREAD_LOCAL.get() != null) {
                        writer.append("<div>" + STATUS_THREAD_LOCAL.get() + "</div>");
                    }
                    writer.append("</body>")
                    .append("</html>");
            writer.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        CrudStatus status = dispatcher.execute(Actions.ADD, req, resp);
        if (status == CrudStatus.INSERTION_SUCCESS) {
            resp.sendRedirect(req.getContextPath() + "/list");
        } else {
            USER_THREAD_LOCAL.set(new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email")));
            STATUS_THREAD_LOCAL.set(status);
            doGet(req, resp);
        }
    }
}
