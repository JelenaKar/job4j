package ru.job4j.array;

import java.util.Arrays;

/**
 * Класс массива с дублирующимися строковыми значениями.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ArrayDuplicate {

    /**
     * Удаляет дублирующиеся строковые значения.
     * @param array исходный массив с дублирующимися значениями.
     * @return преобразованный массив с уникальными строковыми значениями.
     */
    public String[] remove(String[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (array[i].equals(array[j])) {
                    array[j] = array[n - 1];
                    n--;
                }
            }
        }
        array = Arrays.copyOf(array, n);
        return array;
    }
}
