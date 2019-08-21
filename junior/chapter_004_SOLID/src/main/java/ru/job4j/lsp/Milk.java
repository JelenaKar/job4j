package ru.job4j.lsp;

/**
 * Продукт вида "молоко".
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public class Milk extends Food {

    public static final double DISCOUNT_VALUE = 0.5;
    private static final long LIFE_PERIOD = 5L * 24 * 60 * 60 * 1000;

    public Milk(String name, long createDate, double price) {
        super(name, createDate, createDate + LIFE_PERIOD, price, DISCOUNT_VALUE);
    }
}
