package ru.job4j.sort;

/**
 * Класс пользователь, реализующий интерфейс Comparable.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class User implements Comparable<User> {

    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(User o) {
        int k = this.age - o.age;
        return (k != 0) ? k : this.name.compareTo(o.name);
    }
}
