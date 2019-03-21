package ru.job4j.set;

import ru.job4j.list.ContainerList;

import java.util.Iterator;
import java.util.Objects;

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
            if (value != null && value.equals(elem) || value == null && elem == null) {
                return;
            }
        }
        list.add(value);
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleSet<?> simpleSet = (SimpleSet<?>) o;
        return Objects.equals(list, simpleSet.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
