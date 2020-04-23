package ru.job4j.crud;

/**
 * Сервлет, осуществляющий редактирование пользователя.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public class UserUpdateServlet extends UserBaseServlet {

    public UserUpdateServlet() {
        this.button = "Сохранить изменения";
        this.title = "Редактирование пользователя";
        this.action = "update";
        this.success = CrudStatus.UPDATE_SUCCESS;
    }
}
