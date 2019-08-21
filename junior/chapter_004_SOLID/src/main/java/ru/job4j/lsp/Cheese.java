package ru.job4j.lsp;

/**
 * Класс продукта вида "сыр".
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public class Cheese extends Food {

    private static final double DISCOUNT_VALUE = 0.3;
    private static final long LIFE_PERIOD = (60L * 24 * 60 * 60 * 1000);

    public Cheese(String name, long createDate, double price) {
        super(name, createDate, createDate + LIFE_PERIOD, price, DISCOUNT_VALUE);
    }
}
