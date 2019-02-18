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
public abstract class Queen implements Figure {
    protected final Cell position;

    /**
     * Конструктор, задаёт положение ферзя.
     * @param position - ячейка, где позиционирован ферзь.
     */
    public Queen(final Cell position) {
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
        if (!(this.isDiagonal(source, dest)
                || this.isHorizontal(source, dest)
                || this.isVertical(source, dest))) {
            throw new ImpossibleMoveException("Impossible move for queen!");
        }

        int len = (this.isDiagonal(source, dest) || this.isHorizontal(source, dest)) ? abs(dest.x - source.x) : abs(dest.y - source.y);
        Cell[] steps = new Cell[len];

        int deltaX = 0;
        if ((dest.x - source.x) != 0) {
            deltaX = ((dest.x - source.x) > 0) ? 1 : -1;
        }

        int deltaY = 0;
        if ((dest.y - source.y) != 0) {
            deltaY = ((dest.y - source.y) > 0) ? 1 : -1;
        }

        for (int i = 1; i <= steps.length; i++) {
            steps[i - 1] = Cell.values()[8 * (source.x + deltaX * i) + source.y + deltaY * i];
        }

        return steps;
    }
}
