package ru.job4j.array;

/**
 * Класс массив квадратов натуральных чисел.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Square {

    /**
     * Метод вычисления квадратов натуральных чисел.
     * @param n количество натуральных чисел (длина массива).
     * @return массив квадратов натуральных чисел заданной длины.
     */
    public int[] calculate(int n) {

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = (i + 1) * (i + 1);
        }

        return result;

    }

}
