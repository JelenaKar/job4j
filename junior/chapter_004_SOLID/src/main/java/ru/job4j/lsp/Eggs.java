package ru.job4j.lsp;

/**
 * Класс продукта вида "яйца".
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public class Eggs extends Food {

    public static final double DISCOUNT_VALUE = 0.6;
    private static final long LIFE_PERIOD = 25L * 24 * 60 * 60 * 1000;

    public Eggs(String name, long createDate, double price) {
        super(name, createDate, createDate + LIFE_PERIOD, price, DISCOUNT_VALUE);
    }
}