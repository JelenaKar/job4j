package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.Arrays.*;

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
        stream(input).map(
                row -> stream(row).boxed().collect(Collectors.toList())
        ).forEach(list::addAll);
        return list;
    }
}
