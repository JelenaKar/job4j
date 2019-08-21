package ru.job4j.lsp;

/**
 * Класс огурец.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Cucumber extends Vegetable {

    public static final double DISCOUNT_VALUE = 0.6;
    private static final long LIFE_PERIOD = 20L * 24 * 60 * 60 * 1000;

    public Cucumber(String name, long createDate, double price) {
        super(name, createDate, createDate + LIFE_PERIOD, price, DISCOUNT_VALUE);
    }
}
