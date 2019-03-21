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
    private SimpleStack<E> reverse = new SimpleStack<>();

    /**
     * Метод извлечения первого добавленного элемента из очереди.
     * @return возвращает извлекаемый элемент из очереди, одновременно удаляя его.
     * @throws NoSuchElementException если очередь пуста.
     */
    public E poll() {
        this.overtakeStack(straight, reverse);
        return reverse.poll();
    }

    /**
     * Метод добавления элемента в очередь.
     * @param value - добавляемый элемент.
     */
    public void push(E value) {
        this.overtakeStack(reverse, straight);
        straight.push(value);
    }

    /**
     * Метод получения размера очереди.
     * @return возвращает текущее количество элементов в очереди.
     */
    public int size() {
        return straight.size() + reverse.size();
    }

    private void overtakeStack(SimpleStack<E> sender, SimpleStack<E> receiver) {
        int len = sender.size();
        for (int i = 0; i < len; i++) {
            receiver.push(sender.poll());
        }
    }
}