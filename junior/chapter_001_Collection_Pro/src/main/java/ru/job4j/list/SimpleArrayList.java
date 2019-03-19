package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * Класс SimpleArrayList.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleArrayList<E> {

    private int size;
    private Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     * @param data - новый объект с данными.
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Метод удаления первого элемента в списке.
     * @return возвращает удалённый элемент.
     * @throws NoSuchElementException если массив пустой.
     */
    public E delete() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        E res = this.first.date;
        this.first = this.first.next;
        this.size--;
        return res;
    }

    /**
     * Метод получения элемента по индексу.
     * @param index - индекс элемента в массиве.
     * @return возвращает объект с данными, находящийся по искомому индексу в массиве.
     * @throws NoSuchElementException если индекс некорректный.
     */
    public E get(int index) {
        if (index >= size) {
            throw new NoSuchElementException();
        }
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Метод получения размера коллекции.
     * @return возвращает текущий размер массива.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}