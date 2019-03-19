package ru.job4j.generic;

/**
 * Класс-пользователь.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class User extends Base {

    private String name;

    public String getName() {
        return name;
    }

    protected User(String id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + '}';
    }
}