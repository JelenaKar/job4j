package ru.job4j.lsp;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Класс абстрактного хранилища.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public abstract class Storage {

    protected Queue<Food> storage;

    public Storage(Queue<Food> storage) {
        this.storage = storage;
    }

    public Queue<Food> extractStorage() {
        Queue<Food> result = new LinkedList<>(storage);
        this.storage.clear();
        return result;
    }

    public abstract boolean accept(Food food);

    public Queue<Food> getStorage() {
        return this.storage;
    }
}
