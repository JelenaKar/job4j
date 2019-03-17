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
    private int nextIndex = -1;
    private final int[] values;

    public EvenIterator(final int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        int i = this.lastIndex;
        boolean result = true;
        for (int j = i + 1; j < this.values.length; j++) {
            if (this.values[j] % 2 == 0) {
                i = j;
                break;
            }
        }
        if (i == -1 || i == this.lastIndex) {
            result = false;
        } else {
            this.nextIndex = i;
        }
        return result;
    }

    @Override
    public Object next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        } else {
            this.lastIndex = this.nextIndex;
            return this.values[this.lastIndex];
        }
    }
}
