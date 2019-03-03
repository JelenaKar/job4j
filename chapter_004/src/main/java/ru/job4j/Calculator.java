package ru.job4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Класс, производящий вычисления.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Calculator {

    /**
     * Возвращает значение произвольной функции в целочисленном диапазоне аргументов.
     * @param start - начальное значение диапазона (включительно).
     * @param end - конечное значение диапазона (не включительно).
     * @param func - заданная произвольная функция.
     * @return список значений фунцкии из диапазона.
     */
    List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int i = start; i < end; i++) {
            result.add(func.apply((double) i));
        }
        return result;
    }
}
