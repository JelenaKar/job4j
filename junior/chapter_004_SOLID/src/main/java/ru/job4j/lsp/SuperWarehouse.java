package ru.job4j.lsp;

import java.util.Queue;

/**
 * Хранилище с расширенной вместительностью.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class SuperWarehouse extends UsualWarehouse {

    public SuperWarehouse(Queue<Food> storage) {
        super(storage);
        this.capacity = 25;
    }
}
