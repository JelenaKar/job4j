package ru.job4j.stream;

import java.util.Arrays;

/**
 * Класс, производящий фильтрацию массивов.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class ArrayFilter {

    /**
     * Отфильтровывает чётные элементы целочисленного массива и возвращает сумму их квадратов.
     * @param array - исходный целочисленный массив.
     * @return сумму квадратов чётных элементов массива.
     */
    public static int streamArrayFilter(int[] array) {
        return Arrays.stream(array)
                .filter(x -> x % 2 == 0).map(x -> x * x).sum();
    }

}
