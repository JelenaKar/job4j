package ru.job4j.tictactoe;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * Класс логики игры в крестики/нолики.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    /**
     * Проверяет заполнена ли строка/столбец/диагонали крестиками или ноликами.
     * @param predicate - функция-предикатор для крестика или нолика.
     * @param startX - начальное значение координаты Х.
     * @param startY - начальное значение координаты Y.
     * @param deltaX - направление изменения координаты Х.
     * @param deltaY - направление изменения координаты Y.
     * @return true - если заполнена строка/столбец/диагональ крестиками или ноликами, false - в противном случае.
     */
    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int i = 0; i != this.table.length; i++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean isWinner(Predicate<Figure3T> predicate) {
        long count = IntStream.range(0, this.table.length)
                .dropWhile(i -> !this.fillBy(predicate, i, 0, 0, 1)
                        && !this.fillBy(predicate, 0, i, 1, 0)
                && !this.fillBy(predicate, 0, 0, 1, 1)
                && !this.fillBy(predicate, this.table.length - 1, 0, -1, 1))
                .count();
        return count > 0;
    }

    /**
     * Проверяет победили ли крестики.
     * @return true - если крестики победили, false - в противном случае.
     */
    public boolean isWinnerX() {
        return this.isWinner(Figure3T::hasMarkX);
    }

    /**
     * Проверяет победили ли нолики.
     * @return true - если нолики победили, false - в противном случае.
     */
    public boolean isWinnerO() {
        return this.isWinner(Figure3T::hasMarkO);
    }

    /**
     * Проверяет имеются ли свободные поля.
     * @return true - если свободные поля имеются, false - в противном случае.
     */
    public boolean hasGap() {
        return !Arrays.stream(this.table)
                .allMatch(
                        row -> Arrays.stream(row)
                                .allMatch(cell -> cell.hasMarkO() || cell.hasMarkX())
                );
    }
}