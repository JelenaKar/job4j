package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * Класс простейшей очереди SimpleQueue.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleQueue<E> {
    private SimpleStack<E> straight = new SimpleStack<>();

    /**
     * Метод извлечения первого добавленного элемента из очереди.
     * @return возвращает извлекаемый элемент из очереди, одновременно удаляя его.
     * @throws NoSuchElementException если очередь пуста.
     */
    public E poll() {
        SimpleStack<E> reverse = new SimpleStack<>();
        while (straight.size() != 0) {
            reverse.push(straight.poll());
        }
        E res = reverse.poll();
        while (reverse.size() != 0) {
            straight.push(reverse.poll());
        }
        return res;
    }

    /**
     * Метод добавления элемента в очередь.
     * @param value - добавляемый элемент.
     */
    public void push(E value) {
        straight.push(value);
    }

    /**
     * Метод получения размера очереди.
     * @return возвращает текущее количество элементов в очереди.
     */
    public int size() {
        return straight.size();
    }
}