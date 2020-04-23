package ru.job4j.crud;

/**
 * Сервлет, осуществляющий добавление нового пользователя.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public class UserCreateServlet extends UserBaseServlet {

    public UserCreateServlet() {
        this.title = "Создание пользователя";
        this.action = "add";
        this.button = "Добавить";
        this.success = CrudStatus.INSERTION_SUCCESS;
    }
}
