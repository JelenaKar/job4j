package ru.job4j.crud;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Класс валидации и работы с хранилищем.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public class ValidateService implements Validate {
    private static final ValidateService INSTANCE = new ValidateService();
    private final Store store = DbStore.getInstance();
    private final Pattern pattern = Pattern.compile("^.+@.+\\..{2,3}$");

    private ValidateService() {
    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    /**
     * Добавляет пользователя в базу данных.
     * @param user - объект пользователя.
     * @return статус успешности операции.
     */
    @Override
    public CrudStatus add(User user) {
        if (this.isWrongAttributesNameLoginEmail(user)) {
            return CrudStatus.WRONG_USER_INFO;
        }
        if (!this.pattern.matcher(user.getEmail()).find()) {
            return CrudStatus.WRONG_EMAIL;
        }
        this.store.add(user);
        return CrudStatus.INSERTION_SUCCESS;
    }

    /**
     * Обновляет данные пользователя в базе данных.
     * @param user - объект пользователя.
     * @return статус успешности операции.
     */
    @Override
    public CrudStatus update(User user) {
        if (user == null || this.findById(user.getId()) == null) {
            return CrudStatus.WRONG_ID;
        }
        if (this.isWrongAttributesNameLoginEmail(user)) {
            return CrudStatus.WRONG_USER_INFO;
        }
        if (!this.pattern.matcher(user.getEmail()).find()) {
            return CrudStatus.WRONG_EMAIL;
        }
        this.store.update(user);
        return CrudStatus.UPDATE_SUCCESS;
    }

    /**
     * Удаляет пользователя из базы данных.
     * @param user - объект пользователя.
     * @return статус успешности операции.
     */
    @Override
    public CrudStatus delete(User user) {
        if (user == null || this.findById(user.getId()) == null) {
            return CrudStatus.WRONG_ID;
        }
        this.store.delete(user);
        return CrudStatus.DELETE_SUCCESS;
    }

    /**
     * Возвращает пользователя по его id в БД.
     * @param id - id пользователя.
     * @return объект пользователя.
     */
    @Override
    public User findById(long id) {
        return this.store.findById(id);
    }

    /**
     * Возвращает список всех пользователей из БД.
     * @return список всех пользователей.
     */
    @Override
    public List<User> findAll() {
        return this.store.findAll();
    }

    private boolean isWrongAttributesNameLoginEmail(User user) {
        return  (user.getEmail() == null || user.getEmail().trim().isEmpty()
                || user.getName() == null || user.getName().trim().isEmpty()
                || user.getLogin() == null || user.getLogin().trim().isEmpty());
    }
}
