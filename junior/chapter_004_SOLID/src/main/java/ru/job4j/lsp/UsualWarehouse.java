package ru.job4j.lsp;

import java.util.Queue;

/**
 * Хранилище обычной вместительности.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class UsualWarehouse extends Warehouse {

    public int capacity = 3;

    public UsualWarehouse(Queue<Food> storage) {
        super(storage);
    }

    public boolean acceptVegetable(Vegetable food) {
        return false;
    }

    @Override
    public boolean accept(Food food) {
        boolean res = false;
        try {
            this.acceptVegetable((Vegetable) food);
        } catch (ClassCastException cce) {
            if (this.storage.size() < capacity) {
                res = super.accept(food);
            }
        }
        return res;
    }
}
