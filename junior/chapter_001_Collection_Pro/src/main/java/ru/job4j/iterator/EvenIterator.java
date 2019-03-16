package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс итератор чётных чисел одномерного массива.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class EvenIterator implements Iterator {

    private int lastIndex = -1;
    private final int[] values;

    public EvenIterator(final int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        int i = this.findEvenIndex();
        return !(i == -1 || i == this.lastIndex);
    }

    @Override
    public Object next() {
        if (this.findEvenIndex() == -1 || this.findEvenIndex() == this.lastIndex) {
            throw new NoSuchElementException();
        } else {
            this.lastIndex = this.findEvenIndex();
            return this.values[this.lastIndex];
        }
    }

    private int findEvenIndex() {
        int i = this.lastIndex;
        for (int j = i + 1; j < this.values.length; j++) {
            if (this.values[j] % 2 == 0) {
                i = j;
                break;
            }
        }
        return i;
    }
}
