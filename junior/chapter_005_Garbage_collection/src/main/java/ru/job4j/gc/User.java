package ru.job4j.gc;

/**
 * Тестовый класс User с модифицированным методом finalize.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class User {

    private String name;
    private String surname;
    private String city;
    private int age;

    public User(String name, String surname, String city, int age) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.age = age;
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
        System.out.println(this.name + " destroyed");
    }
}
