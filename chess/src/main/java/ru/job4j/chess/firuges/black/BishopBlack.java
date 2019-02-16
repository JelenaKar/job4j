package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.base.Bishop;

/**
 * Класс - черный слон.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack extends Bishop {

    /**
     * Конструктор, задаёт положение чёрного слона.
     * @param position - ячейка, где позиционирован слон.
     */
    public BishopBlack(final Cell position) {
        super(position);
    }

    /**
     * Метод, копирующий черного слона в новую ячейку.
     * @param dest - ячейка, куда перемещается черный слон.
     * @return фигуру скопированного слона в новой ячейке.
     */
    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }

}
