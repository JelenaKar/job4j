package ru.job4j.max;

/**
 * Сравнение двух целых чисел.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Max {

    /**
     * Находит максимальное из двух чисел.
     * @param first Первое целое число.
     * @param second Второе целое число.
     * @return большее из двух чисел.
     */
    public int max(int first, int second) {
        return (first > second) ? first : second;
    }

    /**
     * Находит максимальное из трёх чисел.
     * @param first Первое целое число.
     * @param second Второе целое число.
     * @param third Третье целое число.
     * @return большее из трёх чисел.
     */
    public int max(int first, int second, int third) {
        return this.max(this.max(first, second), third);
    }
}