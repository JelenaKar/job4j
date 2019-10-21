package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Простейшая блокирующая очередь.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    private final int bound;

    public SimpleBlockingQueue() {
        this.bound = Integer.MAX_VALUE;
    }

    public SimpleBlockingQueue(int bound) {
        this.bound = bound;
    }

    /**
     * Добавляет элемент в очередь, если имеется свободное место.
     * @param value новое добавляемое значение.
     */
    public synchronized void offer(T value) {
        while (queue.size() >= bound) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.offer(value);
        notifyAll();
    }

    /**
     * Извлекает первый элемент из очереди, если она не пуста.
     * @return первый элемент очереди.
     */
    public synchronized T poll() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T res = queue.poll();
        notifyAll();
        return res;
    }

    /**
     * Возвращает количество элементов в очереди.
     * @return целочисленный размер очереди.
     */
    public synchronized int size() {
        return queue.size();
    }
}