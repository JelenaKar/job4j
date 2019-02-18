package ru.job4j.chess.firuges.base;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static java.lang.Math.abs;

public abstract class Knight implements Figure {
    protected final Cell position;
    /**
     * Конструктор, задаёт положение коня.
     * @param position - ячейка, где позиционирован конь.
     */
    public Knight(final Cell position) {
        this.position = position;
    }

    /**
     * Метод, возвращающий текущую позицию коня.
     * @return position - ячейка, где позиционирован слон.
     */
    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        int deltaY = abs(source.y - dest.y);
        int deltaX = abs(source.x - dest.x);
        if (!((deltaY == 2 && deltaX == 1) || (deltaY == 1 && deltaX == 2))) {
            throw new ImpossibleMoveException("Impossible move for knight!");
        }
        Cell[] steps = new Cell[] {dest};
        return steps;
    }
}
