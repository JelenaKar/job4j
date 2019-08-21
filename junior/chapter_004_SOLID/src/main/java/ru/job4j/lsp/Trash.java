package ru.job4j.lsp;

import java.util.Queue;

/**
 * Класс хранилища типа "мусорка".
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Trash extends Storage {

    public Trash(Queue<Food> storage) {
        super(storage);
    }

    @Override
    public boolean accept(Food food) {
        boolean res = false;
        double left = (double) (System.currentTimeMillis() - food.getCreateDate()) / (food.getExpireDate() - food.getCreateDate());
        if (left >= 1) {
            food.clearPrice();
            this.storage.add(food);
            res = true;
        }
        return res;
    }
}
