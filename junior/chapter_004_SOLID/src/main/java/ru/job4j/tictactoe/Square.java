package ru.job4j.tictactoe;

/**
 * Класс игрового поля.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Square {

    public static final int CROSS = 1;
    public static final int ZERO = -1;
    private int size;

    public int[][] getMatrix() {
        return matrix;
    }

    private int[][] matrix;

    public Square(int size) {
        this.size = size;
        matrix = new int[size][size];
    }

    /**
     * Выводит игровое поле с фигурами в консоль.
     */
    public void show() {
        for (int i = 1; i <= (2 * size - 1); i++) {
            for (int j = 1; j <= (2 * size - 1); j++) {
                if (i % 2 == 1) {
                    if (j % 2 == 1) {
                        if (matrix[(i - 1) / 2][(j - 1) / 2] == 0) {
                            System.out.print(" ");
                        } else if (matrix[(i - 1) / 2][(j - 1) / 2] == CROSS) {
                            System.out.print("X");
                        } else {
                            System.out.print("0");
                        }
                    } else {
                        System.out.print("|");
                    }
                } else {
                    if (j % 2 == 1) {
                        System.out.print("-");
                    } else {
                        System.out.print("+");
                    }
                }
                if (j == (2 * size - 1)) {
                    System.out.println();
                }
            }
        }
        System.out.println();
    }

    /**
     * Фиксирует ход игрока на игровом поле.
     * @param arr - массив с координатами и фигурой (крестик/нолик).
     * @return true - если ход удалось добавить на поле, false - в противном случае.
     */
    public boolean insert(int[] arr) {
        boolean res = true;
        if (this.isFree(arr[0], arr[1])) {
            this.matrix[arr[0] - 1][arr[1] - 1] = arr[2];
        } else {
            res = false;
        }
        return res;
    }

    /**
     * Проверяет наличие свободных ячеек на игровом поле.
     * @return true - если на поле имеются свободные ячейки,
     * false - в противном случае.
     */
    public boolean hasFree() {
        boolean res = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == 0) {
                    res = true;
                    break;
                }
                if (res) {
                    break;
                }
            }
        }
        return res;
    }

    private boolean isFree(int x, int y) {
        return this.matrix[x - 1][y - 1] == 0;
    }
}
