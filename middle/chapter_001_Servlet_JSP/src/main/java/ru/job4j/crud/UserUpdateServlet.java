package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет, осуществляющий редактирование пользователя.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public class UserUpdateServlet extends HttpServlet {
    private final Validate logic = ValidateService.getInstance();
    private final ActionDispatcher dispatcher = ActionDispatcher.getInstance();
    private static final ThreadLocal<CrudStatus> STATUS_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<User> USER_THREAD_LOCAL = new ThreadLocal<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            User user;
            if (STATUS_THREAD_LOCAL.get() == null) {
                user = logic.findById(logic.parseStringIntoLong(req.getParameter("id")));
            } else {
                user = USER_THREAD_LOCAL.get();
            }
            String name = "";
            String login = "";
            String email = "";
            if (user != null) {
                name = user.getName();
                login = user.getLogin();
                email = user.getEmail();
            }
            writer.append("<!DOCTYPE html>")
                    .append("<html lang=\"ru\">")
                    .append("<head>")
                        .append("<meta charset=\"UTF-8\">")
                        .append("<title>Редактирование пользователя</title>")
                    .append("</head>")
                    .append("<body>")
                        .append("<form method=\"post\">")
                            .append("<input type=\"text\" name=\"name\" value=\"" + name + "\" placeholder=\"Имя пользователя\">")
                            .append("<input type=\"text\" name=\"login\" value=\"" + login + "\" placeholder=\"Логин\">")
                            .append("<input type=\"text\" name=\"email\" value=\"" + email + "\" placeholder=\"E-mail\">")
                            .append("<input type=\"hidden\" name=\"id\" value=\"" + req.getParameter("id") + "\"/>")
                            .append("<button type=\"submit\">Изменить</button>")
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
        CrudStatus status = dispatcher.execute(Actions.UPDATE, req, resp);
        if (status == CrudStatus.UPDATE_SUCCESS) {
            resp.sendRedirect(req.getContextPath() + "/list");
        } else {
            USER_THREAD_LOCAL.set(new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email")));
            STATUS_THREAD_LOCAL.set(status);
            doGet(req, resp);
        }
    }
}
