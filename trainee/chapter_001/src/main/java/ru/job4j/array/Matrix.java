package ru.job4j.array;

/**
 * Класс квадратной матрицы таблицы умножения.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Matrix {

    /**
     * Создаёт квадратную матрицу заданного размера.
     * @param size размер матрицы.
     * @return квадратную матрицу таблицы умножения заданного размера.
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}
