package ru.job4j.lsp;

import java.util.Queue;

/**
 * Хранилище с низкими температурами.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class ColdWarehouse extends Warehouse {

    public ColdWarehouse(Queue<Food> storage) {
        super(storage);
    }
}
