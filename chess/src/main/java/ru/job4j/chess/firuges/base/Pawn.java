package ru.job4j.chess.firuges.base;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * Класс - абстрактная пешка.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public abstract class Pawn implements Figure {
    protected final Cell position;

    /**
     * Конструктор, задаёт положение пешки.
     * @param position - ячейка, где позиционирована пешка.
     */
    public Pawn(final Cell position) {
        this.position = position;
    }

    /**
     * Метод, возвращающий текущую позицию пешки.
     * @return position - ячейка, где позиционирована пешка.
     */
    @Override
    public Cell position() {
        return this.position;
    }
}
