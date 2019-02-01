package ru.job4j.array;

/**
 * Класс переворачивающий массив.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Turn {

    /**
     * Метод обращающий массив.
     * @param array исходный массив.
     * @return массив с элементами в обратном порядке.
     */
    public int[] back(int[] array) {
        int temp;
        for (int i = 0; i < array.length / 2; i++) {
            temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }
}