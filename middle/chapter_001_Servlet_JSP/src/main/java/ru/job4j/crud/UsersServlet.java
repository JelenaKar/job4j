package ru.job4j.crud;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

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
        List<User> users = logic.findAll();
        req.setAttribute("users", users);
        req.setAttribute("format", getFormat());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/list.jsp");
        requestDispatcher.forward(req, resp);
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