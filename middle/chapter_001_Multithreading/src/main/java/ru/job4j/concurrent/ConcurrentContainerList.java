package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.ContainerList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс динамического потокобезопасного контейнера.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
@ThreadSafe
public class ConcurrentContainerList<E> implements Iterable<E> {

    @GuardedBy("this")
    private final ContainerList<E> list;

    public ConcurrentContainerList() {
        this.list = new ContainerList<>();
    }

    public ConcurrentContainerList(int size) {
        this.list = new ContainerList<>(size);
    }

    /**
     * Метод, добавляющий элемент в контейнер.
     * @param value - новый элемент.
     */
    public synchronized void add(E value) {
        list.add(value);
    }

    /**
     * Возвращает элемент массива по индексу элемента.
     * @param index - индекс элемента.
     * @throws NoSuchElementException если индекс некорректен.
     * @return элемент, находящийся по индексу.
     */
    public synchronized E get(int index) {
        return list.get(index);
    }

    /**
     * Получает размер контейнера.
     * @return текущий размер контейнера с учётом пустых ячеек.
     */
    public synchronized int size() {
        return this.list.size();
    }

    private ContainerList<E> copy(ContainerList<E> container) {
        ContainerList<E> copy = new ContainerList<>(container.size());
        for (E value : container) {
            copy.add(value);
        }
        return copy;
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return copy(list).iterator();
    }
}
