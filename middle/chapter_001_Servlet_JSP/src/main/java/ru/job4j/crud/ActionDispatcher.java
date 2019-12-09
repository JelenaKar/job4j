package ru.job4j.crud;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс-диспетчер действий.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class ActionDispatcher {

    private final Validate logic = ValidateService.getInstance();
    private static final ActionDispatcher INSTANCE = new ActionDispatcher();
    private final Map<Actions, ThrowableBiFunction<HttpServletRequest, HttpServletResponse, Void, IOException>> dispatch = new HashMap<>();
    private static final ThreadLocal<DateFormat> THREAD_CACHE = new ThreadLocal<>();

    private ActionDispatcher() {
        this.dispatch.put(Actions.ADD, this.add());
        this.dispatch.put(Actions.UPDATE, this.update());
        this.dispatch.put(Actions.DELETE, this.delete());
        this.dispatch.put(Actions.READ, this.read());
    }

    public static ActionDispatcher getInstance() {
        return INSTANCE;
    }

    /**
     * Обработчик просмотра всех пользователей.
     * @return handle.
     */
    public ThrowableBiFunction<HttpServletRequest, HttpServletResponse, Void, IOException> read() {
        return (req, resp) -> {
            resp.setContentType("text/html; charset=UTF-8");
            try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
                logic.findAll().forEach(u -> writer.append(String.valueOf(u.getId())).append(". ")
                        .append(u.getName()).append(" ")
                        .append(u.getLogin()).append(" ")
                        .append(u.getEmail()).append(" ")
                        .append(getFormat().format(u.getCreateDate()))
                        .append("<br/>"));
                writer.flush();
            }
            return null;
        };
    }

    /**
     * Обработчик вставки пользователя.
     * @return handle.
     */
    public ThrowableBiFunction<HttpServletRequest, HttpServletResponse, Void, IOException> add() {
        return (req, resp) -> {
            try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
                writer.append(logic.add(new User(req.getParameter("name"),
                        req.getParameter("login"), req.getParameter("email"))).toString());
                writer.flush();
            }
            return null;
        };
    }

    /**
     * Обработчик удаления пользователя.
     * @return handle.
     */
    public ThrowableBiFunction<HttpServletRequest, HttpServletResponse, Void, IOException> delete() {
        return (req, resp) -> {
            try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
                writer.append(logic.delete(logic.findById(logic.parseStringIntoLong(req.getParameter("id")))).toString());
                writer.flush();
            }
            return null;
        };
    }

    /**
     * Обработчик обновления пользователя.
     * @return handle.
     */
    public ThrowableBiFunction<HttpServletRequest, HttpServletResponse, Void, IOException> update() {
        return (req, resp) -> {
            try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
                writer.append(logic.update(new User(logic.parseStringIntoLong(req.getParameter("id")), req.getParameter("name"),
                        req.getParameter("login"), req.getParameter("email"))).toString());
                writer.flush();
            }
            return null;
        };
    }

    /**
     * Load handlers for destinations.
     * @param type Message type.
     * @param handle Handler.
     */
    public void load(Actions type, ThrowableBiFunction<HttpServletRequest, HttpServletResponse, Void, IOException> handle) {
        this.dispatch.put(type, handle);
    }


    public void execute(Actions action, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.dispatch.get(action).apply(req, resp);
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
