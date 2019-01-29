package ru.job4j.max;

/**
 * Сравнение двух целых чисел.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Max {

    /**
     * Идеальный вес для мужщины.
     * @param first Первое целое число.
     * @param second Второе целое число.
     * @return большее из двух чисел.
     */
    public int max(int first, int second) {
        return (first > second) ? first : second;
    }
}