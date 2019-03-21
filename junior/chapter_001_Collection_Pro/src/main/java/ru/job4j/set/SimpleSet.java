package ru.job4j.set;

import ru.job4j.list.ContainerList;

import java.util.Iterator;

/**
 * Класс простейшего набора SimpleSet.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleSet<E> implements Iterable<E> {
    private ContainerList<E> list = new ContainerList<>();

    /**
     * Метод, добавляющий элемент в набор.
     * @param value - новый элемент.
     */
    public void add(E value) {
        for (E elem : list) {
            if (value.equals(elem)) {
                return;
            }
        }
        list.add(value);
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
