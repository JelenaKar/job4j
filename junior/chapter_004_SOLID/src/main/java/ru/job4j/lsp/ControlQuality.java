package ru.job4j.lsp;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Класс, сортирующий продукты по хранилищам.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class ControlQuality {

    private List<Storage> storages;

    public ControlQuality(List<Storage> storages) {
        this.storages = storages;
    }

    /**
     * Метод, сортирующий продукты по хранилищам.
     */
    public void sortProducts() {
        Queue<Food> tmp = new LinkedList<>();
        for (Storage storage : storages) {
            tmp.addAll(storage.extractStorage());
        }
        while (!tmp.isEmpty()) {
            Food food = tmp.poll();
            for (Storage storage : storages) {
                if (storage.accept(food)) {
                    break;
                }
            }
        }
    }
}
