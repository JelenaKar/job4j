package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Класс матрицы, полученной из списка.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConvertList2Array {

    /**
     * Метод, преобразующий список в матрицу с заданным числом строк.
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

    /**
     * Метод, преобразующий список из матриц в единый список.
     * @param list - исходный список.
     * @return одномерный список, полученный из двумерного.
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        list.stream().map(
                row -> Arrays.stream(row).boxed().collect(Collectors.toList())
        ).forEach(result::addAll);
        return result;
    }
}