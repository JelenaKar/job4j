package ru.job4j.crud;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Базовый сервлет, обрабатывающий данные пользователя.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public abstract class UserBaseServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UserBaseServlet.class);
    private static final ThreadLocal<CrudStatus> STATUS_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<User> USER_THREAD_LOCAL = new ThreadLocal<>();
    private final ActionDispatcher dispatcher = ActionDispatcher.getInstance();
    private final Validate logic = ValidateService.getInstance();
    protected String title;
    protected String action;
    protected String button;
    protected CrudStatus success;

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
        req.setAttribute("title", title);
        req.setAttribute("user", user);
        req.setAttribute("status", status);
        req.setAttribute("action", action);
        req.setAttribute("button", button);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/views/user.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        CrudStatus status;
        User user = null;
        FormUploader uploader = new FormUploader(req, this.getServletConfig().getServletContext());
        try {
            uploader.upload();
            user = uploader.getUser();
            user.setId(logic.parseStringIntoLong(req.getParameter("id")));
            status = dispatcher.execute(uploader.getAction(), user);
        } catch (FileUploadException e) {
            LOG.error(e.getMessage());
            status = CrudStatus.FORM_UPLOAD_FAILED;
        }
        if (status == success) {
            resp.sendRedirect(req.getContextPath() + "/list");
        } else {
            USER_THREAD_LOCAL.set(new User(user.getName(), user.getLogin(), user.getEmail(), user.getPhotoid()));
            STATUS_THREAD_LOCAL.set(status);
            doGet(req, resp);
        }
    }
}
