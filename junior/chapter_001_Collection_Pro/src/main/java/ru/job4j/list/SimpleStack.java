package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * Класс простейшего стека SimpleStack.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleStack<E> {

    private SimpleArrayList<E> nodeList = new SimpleArrayList<>();

    /**
     * Метод извлечения последнего добавленного элемента из стека.
     * @return возвращает извлекаемый элемент из стека, одновременно удаляя его.
     * @throws NoSuchElementException если стек пустой.
     */
    public E poll() {
        return nodeList.delete();
    }

    /**
     * Метод добавления элемента в стек.
     * @param value - добавляемый элемент.
     */
    public void push(E value) {
        nodeList.add(value);
    }

    /**
     * Метод получения размера стека.
     * @return возвращает текущее количество элементов стека.
     */
    public int size() {
        return nodeList.getSize();
    }

}