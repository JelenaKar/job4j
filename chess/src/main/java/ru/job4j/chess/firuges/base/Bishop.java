package ru.job4j.chess.firuges.base;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import static java.lang.Math.abs;

/**
 * Класс - абстрактный слон.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public abstract class Bishop implements Figure {
    protected final Cell position;

    /**
     * Конструктор, задаёт положение слона.
     * @param position - ячейка, где позиционирован слон.
     */
    public Bishop(final Cell position) {
        this.position = position;
    }

    /**
     * Метод, возвращающий текущую позицию слона.
     * @return position - ячейка, где позиционирован слон.
     */
    @Override
    public Cell position() {
        return this.position;
    }

    /**
     * Метод, возвращающий массив ячеек пути слона.
     * @param source - начальная точка позиционирования слона.
     * @param dest - точка, в которую перемещается слон.
     * @return steps - все ячейки пути слона.
     */
    @Override
    public Cell[] way(Cell source, Cell dest) {
        if (this.isDiagonal(source, dest)) {
            Cell[] steps = new Cell[abs(dest.x - source.x)];
            int deltaX = ((dest.x - source.x) > 0) ? 1 : -1;
            int deltaY = ((dest.y - source.y) > 0) ? 1 : -1;
            for (int i = 1; i <= steps.length; i++) {
                steps[i - 1] = Cell.values()[8 * (source.x + deltaX * i) + source.y + deltaY * i];
            }
            return steps;
        } else {
            throw new ImpossibleMoveException("Impossible move for bishop!");
        }
    }
}
