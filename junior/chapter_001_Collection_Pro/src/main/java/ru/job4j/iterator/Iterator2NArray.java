package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс итератор для двумерного массива.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Iterator2NArray implements Iterator {

    private final int[][] values;

    private int indexX = 0;
    private int indexY = 0;

    public Iterator2NArray(final int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        if (this.indexY == this.values.length) {
            return false;
        }
        return this.indexX < this.values[this.indexY].length;
    }

    @Override
    public Object next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        int res = this.values[this.indexY][this.indexX++];
        if (this.indexX == this.values[this.indexY].length) {
            this.indexX = 0;
            this.indexY++;
        }
        return res;
    }
}
