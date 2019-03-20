package ru.job4j.list;

import java.util.*;

/**
 * Класс связанного списка NodeList.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class NodeList<E> implements Iterable<E> {

    private int position = 0;
    private Node<E> first;
    private Node<E> last;
    private int modCount = 0;

    /**
     * Метод, добавляющий элемент в список.
     * @param value - новый элемент.
     */
    public void add(E value) {
        this.modCount++;
        Node<E> newLink = new Node<>(value);
        if (position == 0) {
            this.first = newLink;
            this.last = newLink;
        } else {
            this.last.next = newLink;
            newLink.previous = this.last;
            this.last = newLink;
        }
        this.position++;
    }

    /**
     * Метод получения элемента по индексу.
     * @param index - индекс элемента в списке.
     * @return возвращает объект с данными, находящийся по искомому индексу в списке.
     * @throws NoSuchElementException если индекс некорректный.
     */
    public E get(int index) {
        if (index >= position) {
            throw new NoSuchElementException();
        }
        Node<E> result;
        if (index < position / 2) {
            result = this.first;
            for (int i = 1; i <= index; i++) {
                result = result.next;
            }
        } else {
            result = this.last;
            for (int i = 1; i < this.position - index; i++) {
                result = result.previous;
            }
        }
        return result.data;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E data;
        Node<E> previous;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    /**
     * Получает размер списка.
     * @return текущий количество элементов в списке.
     */
    public int size() {
        return this.position;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int currentMod = modCount;
            private Node<E> elem = null;

            @Override
            public boolean hasNext() {
                return elem != last;
            }

            @Override
            public E next() {
                if (this.currentMod < modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                this.elem = (this.elem == null) ? first : this.elem.next;
                return this.elem.data;
            }
        };
    }
}