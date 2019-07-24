package ru.job4j.lsp;

import java.util.Objects;

/**
 * Класс абстрактного продукта.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public abstract class Food {

    protected String name;
    protected long createDate;
    protected long expireDate;
    protected double price;
    protected double discount;

    public Food(String name, long createDate, long expireDate, double price, double discount) {
        this.name = name;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.price = price;
        this.discount = discount;
    }

    protected void setDiscountPrice() {
        this.price = this.discount * this.price;
        this.discount = 1;
    }

    protected void clearPrice() {
        this.price = 0;
    }

    public String getName() {
        return name;
    }

    public long getCreateDate() {
        return createDate;
    }

    public long getExpireDate() {
        return expireDate;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }
}