package ru.job4j.crud;

import java.io.File;
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
    private final Map<Actions, ThrowableFunction<User, CrudStatus, IOException>> dispatch = new HashMap<>();

    private ActionDispatcher() {
        this.dispatch.put(Actions.ADD, this.add());
        this.dispatch.put(Actions.UPDATE, this.update());
        this.dispatch.put(Actions.DELETE, this.delete());
        this.dispatch.put(Actions.REMOVEPHOTO, this.removePhoto());
    }

    public static ActionDispatcher getInstance() {
        return INSTANCE;
    }

    /**
     * Обработчик вставки пользователя.
     * @return handle.
     */
    public ThrowableFunction<User, CrudStatus, IOException> add() {
        return user -> logic.add(user);
    }

    /**
     * Обработчик удаления пользователя.
     * @return handle.
     */
    public ThrowableFunction<User, CrudStatus, IOException> delete() {
        return user -> logic.delete(user);
    }

    /**
     * Обработчик обновления пользователя.
     * @return handle.
     */
    public ThrowableFunction<User, CrudStatus, IOException> update() {
        return user -> logic.update(user);
    }

    /**
     * Обработчик удаления фото пользователя.
     * @return handle.
     */
    public ThrowableFunction<User, CrudStatus, IOException> removePhoto() {
        return user -> {
            boolean delete = new File(User.IMG_DIR + File.separator + user.getPhotoid()).delete();
            if (!delete) {
                return CrudStatus.PHOTO_REMOVE_FAILED;
            }
            return logic.removePhoto(user);
        };
    }

    /**
     * Загружает новый обработчик для действия
     * @param action операция.
     * @param handle обработчик операции.
     */
    public void load(Actions action, ThrowableFunction<User, CrudStatus, IOException> handle) {
        this.dispatch.put(action, handle);
    }


    /**
     * Выполняет заданную операцию.
     * @param action операция.
     * @return статус завершения операции.
     */
    public CrudStatus execute(Actions action, User user) throws IOException {
        return this.dispatch.get(action).apply(user);
    }
}
