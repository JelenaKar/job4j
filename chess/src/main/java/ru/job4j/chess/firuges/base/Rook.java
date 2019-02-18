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
public abstract class Rook implements Figure {
    protected final Cell position;

    /**
     * Конструктор, задаёт положение ферзя.
     * @param position - ячейка, где позиционирован ферзь.
     */
    public Rook(final Cell position) {
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
        if (!(this.isHorizontal(source, dest) || this.isVertical(source, dest))) {
            throw new ImpossibleMoveException("Impossible move for rook!");
        }

        int len = 0;
        int deltaX = 0;
        int deltaY = 0;

        if (this.isHorizontal(source, dest)) {
            len = abs(dest.x - source.x);
            deltaX = ((dest.x - source.x) > 0) ? 1 : -1;
        }

        if (this.isVertical(source, dest)) {
            len = abs(dest.y - source.y);
            deltaY = ((dest.y - source.y) > 0) ? 1 : -1;
        }

         Cell[] steps = new Cell[len];

        for (int i = 1; i <= steps.length; i++) {
            steps[i - 1] = Cell.values()[8 * (source.x + deltaX * i) + source.y + deltaY * i];
        }

        return steps;
    }
}
