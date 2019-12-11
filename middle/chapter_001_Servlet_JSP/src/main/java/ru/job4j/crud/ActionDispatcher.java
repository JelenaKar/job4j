package ru.job4j.crud;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private final Map<Actions, ThrowableBiFunction<HttpServletRequest, HttpServletResponse, CrudStatus, IOException>> dispatch = new HashMap<>();

    private ActionDispatcher() {
        this.dispatch.put(Actions.ADD, this.add());
        this.dispatch.put(Actions.UPDATE, this.update());
        this.dispatch.put(Actions.DELETE, this.delete());
    }

    public static ActionDispatcher getInstance() {
        return INSTANCE;
    }

    /**
     * Обработчик вставки пользователя.
     * @return handle.
     */
    public ThrowableBiFunction<HttpServletRequest, HttpServletResponse, CrudStatus, IOException> add() {
        return (req, resp) -> logic.add(new User(req.getParameter("name"),
                        req.getParameter("login"), req.getParameter("email")));
    }

    /**
     * Обработчик удаления пользователя.
     * @return handle.
     */
    public ThrowableBiFunction<HttpServletRequest, HttpServletResponse, CrudStatus, IOException> delete() {
        return (req, resp) -> logic.delete(logic.findById(logic.parseStringIntoLong(req.getParameter("id"))));
    }

    /**
     * Обработчик обновления пользователя.
     * @return handle.
     */
    public ThrowableBiFunction<HttpServletRequest, HttpServletResponse, CrudStatus, IOException> update() {
        return (req, resp) -> logic.update(new User(logic.parseStringIntoLong(req.getParameter("id")), req.getParameter("name"),
                        req.getParameter("login"), req.getParameter("email")));
    }

    /**
     * Загружает новый обработчик для действия
     * @param action операция.
     * @param handle обработчик операции.
     */
    public void load(Actions action, ThrowableBiFunction<HttpServletRequest, HttpServletResponse, CrudStatus, IOException> handle) {
        this.dispatch.put(action, handle);
    }


    /**
     * Выполняет заданную операцию.
     * @param action операция.
     * @return статус завершения операции.
     */
    public CrudStatus execute(Actions action, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return this.dispatch.get(action).apply(req, resp);
    }
}
