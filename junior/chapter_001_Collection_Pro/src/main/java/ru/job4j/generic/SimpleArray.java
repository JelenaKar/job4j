package ru.job4j.generic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Класс-обёртка над одномерным массивом.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleArray<T> implements Iterable<T> {

    private T[] values;
    private int position = 0;

    public SimpleArray(int size) {
        this.values = (T[]) new Object[size];
    }

    /**
     * Добавляет элемент массива по индексу.
     * @param model - новый элемент массива.
     * @throws ArrayIndexOutOfBoundsException если массив переполнен.
     */
    public void add(T model) {
        if (this.position == this.values.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.values[this.position++] = model;
    }

    /**
     * Возвращает элемент массива по индексу.
     * @param index - индекс массива.
     * @return элемент массива.
     * @throws NoSuchElementException при некорректном индексе.
     */
    public T get(int index) {
        if (index >= this.position) {
            throw new NoSuchElementException();
        }
        return this.values[index];
    }

    /**
     * Заменяет элемент массива по индексу.
     * @param index - индекс массива.
     * @param model - новое значение элемента массива.
     * @throws NoSuchElementException при некорректном индексе.
     */
    public void set(int index, T model) {
        if (index >= this.position) {
            throw new NoSuchElementException();
        }
        this.values[index] = model;
    }

    /**
     * Удаляет элемент массива по индексу, сдвигая все оставшиеся влево.
     * @param index - индекс массива.
     * @throws NoSuchElementException при некорректном индексе.
     */
    public void remove(int index) {
        if (index >= this.position) {
            throw new NoSuchElementException();
        }
        for (int i = index; i < this.position; i++) {
            this.values[i] = this.values[i + 1];
            this.values = Arrays.copyOf(this.values, --this.position);
        }
    }

    /**
     * Получает размерность массива.
     * @return текущую длину массива.
     */
    public int size() {
        return this.position;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < position;
            }

            @Override
            public T next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return values[this.index++];
            }
        };
    }

}
