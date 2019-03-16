package ru.job4j.loop;

/**
 * Счётчик.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Counter {

    /**
     * Находит сумму всех чётных чисел из диапазона.
     * @param start Начало диапазона.
     * @param finish конец диапазона.
     * @return сумму чётных чисел.
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    };
}