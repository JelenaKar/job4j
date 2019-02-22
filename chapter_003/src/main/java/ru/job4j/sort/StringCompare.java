package ru.job4j.sort;

import java.util.Comparator;

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
        int result = 0;
        int i = 0;
        while (i < left.length() && i < right.length()) {
            result = Character.compare(leftChars[i], rightChars[i]);
            if (result != 0) {
                break;
            }
            i++;
        }
        if (result == 0) {
            result = Integer.compare(left.length(), right.length());
        }

        return result;
    }
}