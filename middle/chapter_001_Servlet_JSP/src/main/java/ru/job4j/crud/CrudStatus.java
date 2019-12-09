package ru.job4j.crud;


/**
 * Статус успешности операции.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public enum CrudStatus {
    INSERTION_SUCCESS("Пользователь успешно добавлен"),
    UPDATE_SUCCESS("Пользователь успешно изменен"),
    DELETE_SUCCESS("Пользователь успешно удален"),
    WRONG_EMAIL("Неверный формат email"),
    WRONG_ID("Пользователь с таким id не существует"),
    WRONG_USER_INFO("Не указаны или некорректно указаны поля name, login и email: не могут быть пустыми.");


    private final String levelCode;

    CrudStatus(String levelCode) {
        this.levelCode = levelCode;
    }

    @Override
    public String toString() {
        return levelCode;
    }
}
