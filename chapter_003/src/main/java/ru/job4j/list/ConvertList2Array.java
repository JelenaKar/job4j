package ru.job4j.list;

import java.util.List;

/**
 * Класс матрицы, полученной из списка.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConvertList2Array {

    /**
     * Метод, приобразующий список в матрицу с заданным числом строк.
     * @param list - исходный список.
     * @param rows - заданное число строк.
     * @return список, преобразованный в матрицу.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cols = (int) Math.ceil((double) list.size() / rows);
        int[][] array = new int[rows][cols];
        int i = 0;
        int j = 0;
        for (int l : list) {
            array[i][j++] = l;
            if (j == cols) {
                j = 0;
                i++;
            }
        }
        return array;
    }
}