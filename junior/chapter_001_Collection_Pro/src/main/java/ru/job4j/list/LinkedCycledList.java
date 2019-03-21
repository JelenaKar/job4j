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
        Node<E> current = first.next;
        Node<E>[] temp = (Node<E>[]) new Node[10];
        int position = 1;
        temp[0] = first;
        boolean result = false;
        while (current.next != null) {
            for (Node<E> elem : temp) {
                if (current.equals(elem)) {
                    result = true;
                    break;
                }
            }
            if (result) {
                break;
            } else {
                if (position >= temp.length) {
                    temp = Arrays.copyOf(temp, temp.length * 2);
                }
                temp[position++] = current;
                current = current.next;
            }
        }
        return result;
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