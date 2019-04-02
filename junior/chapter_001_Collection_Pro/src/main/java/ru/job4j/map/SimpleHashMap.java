package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


/**
 * Класс простейшей хэш-карты SimpleHashMap.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleHashMap<K, V> implements Iterable {

    private final static int MIN_SIZE = 4;
    private int position = 0;
    private int modCount = 0;

    private Pair<K, V>[] tab;

    private int hash(K key) {
        return (key == null) ? 0 : key.hashCode() ^ key.hashCode() >>> 16;
    }

    public SimpleHashMap() {
        this.tab = (Pair<K, V>[]) new Pair[MIN_SIZE];
    }

    /**
     * Метод, добавляющий элемент в карту.
     * @param key - значение ключа.
     * @param value - значение по ключу.
     * @return true - если вставка прошла успешно, false - если вставка не была произведена.
     */
    public boolean insert(K key, V value) {
        if (position == size()) {
            resizeTable();
        }
        int ind = (key == null) ? 0 : hash(key) & (size() - 1);
        if (this.tab[ind] == null) {
            position++;
        }
        return this.putVal(this.tab, ind, key, value);
    }

    private void resizeTable() {
        int newSize = size() * 2;
        Pair<K, V>[] newTab = (Pair<K, V>[]) new Pair[newSize];
        for (Pair<K, V> elem : this.tab) {
            this.putVal(newTab, hash(elem.getKey()) & (newSize - 1), elem.getKey(), elem.getValue());
        }
        this.tab = newTab;
    }

    private boolean putVal(Pair<K, V>[] tab, int ind, K key, V value) {
        boolean res = true;
        if ((tab[ind] == null) || (tab[ind] != null && tab[ind].getKey().equals(key))) {
            tab[ind] = new Pair<>(key, value);
            modCount++;
        } else {
            res = false;
        }
        return res;
    }

    /**
     * Метод, получающий значение элемента по ключу.
     * @param key - значение ключа.
     * @return значение.
     */
    public V get(K key) {
        Pair<K, V> elem = this.tab[hash(key) & (size() - 1)];
        return (elem == null) ? null : elem.getKey().equals(key) ? elem.getValue() : null;
    }

    /**
     * Метод, удаляющий элемент из карты по ключу.
     * @param key - значение ключа.
     * @return true - если элемент был удалён, false - в противном случае.
     */
    public boolean delete(K key) {
        boolean res = false;
        if (this.tab[size() - 1 & hash(key)].getKey().equals(key)) {
            if (this.tab[size() - 1 & hash(key)] != null) {
                this.tab[size() - 1 & hash(key)] = null;
                modCount++;
                position--;
                res = true;
            }
        }
        return res;
    }

    /**
     * Получает размер карты.
     * @return текущее размер карты с учётом пустых ячеек.
     */
    public int size() {
        return this.tab.length;
    }

    /**
     * Получает множество пар "ключ-значение".
     * @return множество пар "ключ-значение", в котором могут быть значения null.
     */
    public Pair<K, V>[] entrySet() {
        return this.tab;
    }

    @Override
    public Iterator iterator() {
        return new Iterator<>() {
            private int currentMod = modCount;
            private int ind = 0;
            private int pos = 0;

            @Override
            public boolean hasNext() {
                boolean res = true;
                int i = ind;
                if (i == size()) {
                    res = false;
                } else {
                    do {
                        if (tab[i++] != null) {
                            ind = --i;
                            pos++;
                            break;
                        }
                    } while (i < size() || pos < position);
                }
                return res;
            }

            @Override
            public Pair<K, V> next() {
                if (this.currentMod < modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return tab[ind++];
            }
        };
    }

    public class Pair<T, E> {
        private T key;
        private E value;

        public Pair() {
        }

        public Pair(T key, E value) {
            this.key = key;
            this.value = value;
        }

        public T getKey() {
            return key;
        }

        public E getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(key, pair.key) && Objects.equals(value, pair.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}