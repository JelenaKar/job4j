package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Сервлет, осуществляющий просмотр и удаление пользователей.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class UsersServlet extends HttpServlet {
    private final Validate logic = ValidateService.getInstance();
    private final ActionDispatcher dispatcher = ActionDispatcher.getInstance();
    private static final ThreadLocal<DateFormat> THREAD_CACHE = new ThreadLocal<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            writer.append("<!DOCTYPE html>")
                    .append("<html lang=\"ru\">")
                    .append("<head>")
                        .append("<meta charset=\"UTF-8\">")
                        .append("<title>Просмотр всех пользователей</title>")
                    .append("</head>")
                    .append("<body>")
                    .append("<table border=\"1px\">");
                        logic.findAll().forEach(u -> writer
                        .append("<tr>")
                        .append("<td>").append(String.valueOf(u.getId())).append("</td>")
                        .append("<td>").append(u.getName()).append("</td>")
                        .append("<td>").append(u.getLogin()).append("</td>")
                        .append("<td>").append(u.getEmail()).append("</td>")
                        .append("<td>").append(getFormat().format(u.getCreateDate())).append("</td>")
                        .append("<td>")
                            .append("<form method=\"post\" style=\"display:inline\">")
                                .append("<input type=\"hidden\" name=\"action\" value=\"delete\"/>")
                                .append("<input type=\"hidden\" name=\"id\" value=\"" + u.getId() + "\"/>")
                                .append("<button type=\"submit\">Удалить</button>")
                            .append("</form>")
                            .append("<form method=\"get\" style=\"display:inline\" action=\"" + req.getContextPath() + "/edit\" >")
                                .append("<input type=\"hidden\" name=\"id\" value=\"" + u.getId() + "\"/>")
                                .append("<button type=\"submit\">Редактировать</button>")
                            .append("</form>")
                        .append("</td>")
                        .append("</tr>"));
                   writer.append("</table>")
                         .append("<form action=\"" + req.getContextPath() + "/create\">")
                            .append("<button type=\"submit\">Добавить</button>")
                         .append("</form>")
                .append("</body>")
            .append("</html>");
            writer.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher.execute(Actions.DELETE, req, resp);
        doGet(req, resp);
    }

    private static DateFormat getFormat() {
        DateFormat format = THREAD_CACHE.get();
        if (format == null) {
            format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            THREAD_CACHE.set(format);
        }
        return format;
    }
}