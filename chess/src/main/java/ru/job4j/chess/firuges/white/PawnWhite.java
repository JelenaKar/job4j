package ru.job4j.chess.firuges.white;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.base.Pawn;

/**
 * Класс - белая пешка.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PawnWhite extends Pawn {

    public PawnWhite(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        if (!(source.y == dest.y - 1 && source.x == dest.x)) {
            throw new ImpossibleMoveException("Impossible move for pawn!");
        }
        Cell[] steps = new Cell[] {dest};
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnWhite(dest);
    }
}
