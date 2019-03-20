package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс динамического контейнера ContainerList.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ContainerList<E> implements Iterable<E> {

    private E[] container;
    private int position = 0;
    private int modCount = 0;
    private static final int MIN_SIZE = 10;

    public ContainerList() {
        this.container = (E[]) new Object[MIN_SIZE];
    }

    public ContainerList(int size) {
        this.container = (E[]) new Object[size];
    }

    /**
     * Метод, добавляющий элемент в контейнер.
     * @param value - новый элемент.
     */
    public void add(E value) {
        this.modCount++;
        this.defineLength();
        this.container[this.position++] = value;
    }

    /**
     * Возвращает элемент массива по индексу элемента.
     * @param index - индекс элемента.
     * @throws NoSuchElementException если индекс некорректен.
     * @return элемент, находящийся по индексу.
     */
    public E get(int index) {
        if (index >= this.position) {
            throw new NoSuchElementException();
        }
        return this.container[index];
    }

    /**
     * Получает размер контейнера.
     * @return текущий размер контейнера с учётом пустых ячеек.
     */
    public int size() {
        return this.container.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int currentMod = modCount;
            private int ind = 0;

            @Override
            public boolean hasNext() {
                return ind < position;
            }

            @Override
            public E next() {
                if (this.currentMod < modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[ind++];
            }
        };
    }

    private void defineLength() {
        if (this.position >= this.container.length) {
            this.container = Arrays.copyOf(this.container, this.container.length * 2);
        }
    }
}
