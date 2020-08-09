package ru.job4j.crud;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", logic.findAll());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/views/list.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher.execute(Actions.DELETE, logic.findById(logic.parseStringIntoLong(req.getParameter("id"))));
        doGet(req, resp);
    }
}