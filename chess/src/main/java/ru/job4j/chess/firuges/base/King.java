package ru.job4j.chess.firuges.base;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static java.lang.Math.abs;

/**
 * Класс - абстрактный ферзь.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public abstract class King implements Figure {
    protected final Cell position;

    /**
     * Конструктор, задаёт положение ферзя.
     * @param position - ячейка, где позиционирован ферзь.
     */
    public King(final Cell position) {
        this.position = position;
    }

    /**
     * Метод, возвращающий текущую позицию ферзя.
     * @return position - ячейка, где позиционирован ферзь.
     */
    @Override
    public Cell position() {
        return this.position;
    }

    /**
     * Метод, возвращающий массив ячеек пути ферзя.
     * @param source - начальная точка позиционирования ферзя.
     * @param dest - точка, в которую перемещается ферзь.
     * @return steps - все ячейки пути ферзя.
     */
    @Override
    public Cell[] way(Cell source, Cell dest) {

        int deltaX = source.x - dest.x;
        int deltaY = source.y - dest.y;

        if (!((abs(deltaX) == 1 && deltaY == 0) || (deltaX == 0 && abs(deltaY) == 1))) {
            throw new ImpossibleMoveException("Impossible move for king!");
        }

        Cell[] steps = new Cell[] {dest};
        return steps;
    }
}
