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
    INSERTION_FAILED("Ошибка при добавлении нового пользователя"),
    UPDATE_SUCCESS("Пользователь успешно изменен"),
    UPDATE_FAILED("Ошибка при изменении данных пользователя"),
    DELETE_SUCCESS("Пользователь успешно удален"),
    DELETE_FAILED("Ошибка при удалении пользователя"),
    WRONG_EMAIL("Неверный формат email"),
    WRONG_ID("Пользователь с таким id не существует"),
    WRONG_USER_INFO("Не указаны или некорректно указаны поля name, login и email: не могут быть пустыми."),
    FORM_UPLOAD_FAILED("Ошибка загрузки формы"),
    PHOTO_REMOVE_FAILED("Ошибка при удалении фото"),
    PHOTO_REMOVE_SUCCESS("Фото успешно удалено");


    private final String levelCode;

    CrudStatus(String levelCode) {
        this.levelCode = levelCode;
    }

    @Override
    public String toString() {
        return levelCode;
    }
}
