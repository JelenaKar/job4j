package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс-конвертер итераторов.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Converter {

    /**
     * Преобразует итератор итераторов в единый итератор.
     * @param iterators - итератор итераторов.
     * @return единый итератор.
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> iterators) {
        return new Iterator<>() {
            Iterator<Integer> current = iterators.next();

            @Override
            public boolean hasNext() {
                if (!current.hasNext()) {
                    while (iterators.hasNext()) {
                        current = iterators.next();
                        if (current.hasNext()) {
                            break;
                        }
                    }
                }
                return this.current.hasNext();
            }
            @Override
            public Integer next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return this.current.next();
            }
        };
    }
}
