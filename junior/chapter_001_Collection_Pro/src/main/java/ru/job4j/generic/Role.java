package ru.job4j.generic;

/**
 * Класс-роль.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Role extends Base {

    private String action;

    protected Role(String id, String action) {
        super(id);
        this.action = action;
    }

    @Override
    public String toString() {
        return "Role{" + "action='" + action + '\'' + '}';
    }

    public String getAction() {
        return action;
    }
}
