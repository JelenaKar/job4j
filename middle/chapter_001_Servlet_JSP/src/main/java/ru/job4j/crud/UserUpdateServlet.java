package ru.job4j.crud;

import javax.servlet.RequestDispatcher;
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
        String status = "";
        User user;
        if (STATUS_THREAD_LOCAL.get() == null) {
            user = logic.findById(logic.parseStringIntoLong(req.getParameter("id")));
        } else {
            user = USER_THREAD_LOCAL.get();
            status = STATUS_THREAD_LOCAL.get().toString();
        }
        req.setAttribute("title", "Редактирование пользователя");
        req.setAttribute("user", user);
        req.setAttribute("status", status);
        req.setAttribute("button", "Изменить");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user.jsp");
        requestDispatcher.forward(req, resp);
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
