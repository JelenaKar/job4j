package ru.job4j.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Класс-компаратор для строк.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class StringCompare implements Comparator<String> {

    /**
     * Метод, сравнивающий две строки, аналог String.compareTo.
     * @param left первая строка.
     * @param right вторая строка.
     * @return 0 - если строки равны; 1 и -1 - если первая строка лексикографически больше или меньше соответственно.
     */
    @Override
    public int compare(String left, String right) {
        char[] leftChars = left.toCharArray();
        char[] rightChars = right.toCharArray();
        int diff = IntStream.range(0, Integer.min(leftChars.length, rightChars.length))
                .filter(i -> leftChars[i] != rightChars[i])
                .findFirst().orElse(-1);

        return (diff == -1) ? Integer.compare(left.length(), right.length())
                : Integer.compare(leftChars[diff], rightChars[diff]);
    }
}