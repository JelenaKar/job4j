package ru.job4j.lsp;

import java.util.Queue;

/**
 * Хранилище - "умная" утилизация.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class SmartTrash extends Trash {

    public SmartTrash(Queue<Food> storage) {
        super(storage);
    }

    @Override
    public boolean accept(Food food) {
        boolean res = false;
        if (!food.canReproduct) {
            super.accept(food);
        }
        return res;
    }
}
