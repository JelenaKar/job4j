package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Класс итератор для двумерного массива.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Iterator2NArray implements Iterator {

    private final int[][] values;

    private int indexX = 0;
    private int indexY = -1;

    public Iterator2NArray(final int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return !(this.indexY == this.values[this.indexX].length - 1 && this.indexX == this.values.length - 1);
    }

    @Override
    public Object next() {
        if (this.indexY == this.values[this.indexX].length - 1) {
            this.indexY = 0;
            this.indexX++;
        } else {
            this.indexY++;
        }
        return this.values[this.indexX][this.indexY];
    }
}
