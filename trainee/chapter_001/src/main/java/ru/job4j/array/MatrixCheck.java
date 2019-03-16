package ru.job4j.array;

/**
 * Класс, проверяющий квадратную булеву матрицу на однородность.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class MatrixCheck {

    /**
     * Проверка диагоналей матрицы на однородность.
     * @param data исходный булев массив.
     * @return true в случае, если все элементы диагоналей однородны и false в противном случае.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        int n = data.length;
        for (int i = 1; i < n; i++) {
            if ((data[i][i] != data[i - 1][i - 1]) || (data[i][n - i - 1] != data[i - 1][n - i])) {
                result = false;
                break;
            }
        }
        return result;
    }
}