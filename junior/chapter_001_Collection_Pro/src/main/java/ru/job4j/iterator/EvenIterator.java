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

    private int lastIndex = 0;
    private final int[] values;

    public EvenIterator(final int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = this.lastIndex; i < this.values.length; i++) {
            if (this.values[i] % 2 == 0) {
                this.lastIndex = i;
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        } else {
            return this.values[this.lastIndex++];
        }
    }
}
