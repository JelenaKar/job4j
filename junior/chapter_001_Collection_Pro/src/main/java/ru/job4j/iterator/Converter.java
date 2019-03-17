package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Класс-конвертер итераторов.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Converter {

    /**
     * Преобразует итератор итераторов в единый итератор.
     * @param iteratorOfIterators - итератор итераторов.
     * @return единый итератор.
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> iteratorOfIterators) {
        return new Iterator<>() {
            Iterator<Integer> currentIterator = iteratorOfIterators.next();

            @Override
            public boolean hasNext() {
                return this.returnCurrentIterator().hasNext();
            }
            @Override
            public Integer next() {
                return this.returnCurrentIterator().next();
            }

            private Iterator<Integer> returnCurrentIterator() {
                this.currentIterator = currentIterator.hasNext() ? this.currentIterator : iteratorOfIterators.hasNext() ? iteratorOfIterators.next() : this.currentIterator;
                return this.currentIterator;
            }
        };
    }
}
