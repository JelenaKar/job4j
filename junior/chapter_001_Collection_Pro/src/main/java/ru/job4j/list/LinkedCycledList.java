package ru.job4j.list;

import java.util.Arrays;

/**
 * Класс связанного цикла, который может зацикливаться.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class LinkedCycledList<E> {

    /**
     * Проверяет зациклен ли связанный список.
     * @param first первый элемент списка.
     * @return true если список зациклен и false в противном случае.
     */
    public boolean hasCycle(Node<E> first) {

        if (first == null) {
            return false;
        }

        Node<E> slow = first, fast = first;
        int taken = 0, power = 2;

        while (fast.next != null) {
            fast = fast.next;
            taken++;
            if (slow == fast) {
                return true;
            }

            if (taken == power) {
                taken = 0;
                power *= 2;
                slow = fast;
            }
        }
        return false;
    }
}

/**
 * Класс предназначен для хранения данных.
 */
class Node<E> {
    public E data;
    public Node<E> next;
    public Node(E value) {
        this.data = value;
    }
}