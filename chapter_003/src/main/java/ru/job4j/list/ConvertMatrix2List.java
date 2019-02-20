package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс списка, полученного из матрицы.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConvertMatrix2List {

    /**
     * Метод, преобразующий матрицу в список.
     * @param input - исходная матрица.
     * @return список, полученный из матрицы.
     */
    public List<Integer> toList(int[][] input) {
        List<Integer> list = new ArrayList<>();
        for (int[] rows : input) {
            for (int cells : rows) {
                list.add(cells);
            }
        }
        return list;
    }
}
