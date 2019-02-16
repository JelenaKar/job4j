package ru.job4j.chess.firuges.white;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.base.Bishop;

/**
 * Класс - белый слон.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopWhite extends Bishop {

    /**
     * Конструктор, задаёт положение белого слона.
     * @param position - ячейка, где позиционирован слон.
     */
    public BishopWhite(final Cell position) {
        super(position);
    }

    /**
     * Метод, копирующий белого слона в новую ячейку.
     * @param dest - ячейка, куда перемещается белый слон.
     * @return фигуру скопированного слона в новой ячейке.
     */
    @Override
    public Figure copy(Cell dest) {
        return new BishopWhite(dest);
    }
}
