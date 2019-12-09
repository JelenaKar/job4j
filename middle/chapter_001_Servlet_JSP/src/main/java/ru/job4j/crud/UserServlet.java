package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.IntStream;

/**
 * Сервлет, осуществляющий работу с данными пользователя.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class UserServlet extends HttpServlet {
    private final ActionDispatcher dispatcher = ActionDispatcher.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        dispatcher.execute(Actions.READ, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            try {
                Actions action = Actions.valueOf(req.getParameter("action").toUpperCase());
                dispatcher.execute(action, req, resp);
            } catch (IllegalArgumentException | NullPointerException ie) {
                writer.append("Отсутствует или неверно указан аргумент action. Допустимые значения: ");
                IntStream.range(0, Actions.values().length)
                        .mapToObj(i -> String.valueOf(Actions.values()[i]).toLowerCase()
                                + (i < Actions.values().length - 1 ? ", " : "."))
                        .forEach(writer::append);
            }
            writer.flush();
        }
    }
}