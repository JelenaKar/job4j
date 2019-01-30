package ru.job4j.loop;

/**
 * Факториал.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Factorial {

    /**
     * Находит факториал неотрицательного числа.
     * @param n число, для которого рассчитывается факториал.
     * @return n!.
     */
    public int calc(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}