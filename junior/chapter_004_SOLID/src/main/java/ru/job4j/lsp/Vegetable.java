package ru.job4j.lsp;

/**
 * Абстактный класс овоща.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public abstract class Vegetable extends Food {

    public Vegetable(String name, long createDate, long expireDate, double price, double discount) {
        super(name, createDate, expireDate, price, discount);
    }

    public Vegetable(String name, long createDate, long expireDate, double price, double discount, boolean canReproduct) {
        super(name, createDate, expireDate, price, discount, canReproduct);
    }
}
