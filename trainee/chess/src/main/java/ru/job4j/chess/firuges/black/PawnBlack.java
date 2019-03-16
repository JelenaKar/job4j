package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.base.Pawn;

/**
 * Класс - чёрная пешка.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PawnBlack extends Pawn {

    public PawnBlack(final Cell position) {
        super(position);
    }

    /**
     * Метод, возвращающий массив ячеек пути черной пешки.
     * @param source - начальная точка позиционирования пешки.
     * @param dest - точка, в которую перемещается пешка.
     * @return steps - все ячейки пути пешки.
     */
    @Override
    public Cell[] way(Cell source, Cell dest) {
        if (!(source.y == dest.y + 1 && source.x == dest.x)) {
            throw new ImpossibleMoveException("Impossible move for pawn!");
        }
        Cell[] steps = new Cell[] {dest};
        return steps;
    }

    /**
     * Метод, копирующий черную пешку в новую ячейку.
     * @param dest - ячейка, куда перемещается черная пешка.
     * @return фигуру скопированной черной пешки в новой ячейке.
     */
    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest);
    }
}
