package ru.job4j.concurrent;

/**
 * Базовый класс модели.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Base {

    private int id;
    private int version;
    private String name;

    public Base(int id, String name) {
        this.id = id;
        this.name = name;
        this.version = 0;
    }

    public Base(int id, String name, int version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public Base(Base another) {
        this.id = another.id;
        this.name = another.name;
        this.version = another.version;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
