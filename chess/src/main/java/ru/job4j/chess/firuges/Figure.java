package ru.job4j.chess.firuges;

import static java.lang.Math.abs;

public interface Figure {
    Cell position();

    Cell[] way(Cell source, Cell dest);

    default String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }

   default boolean isDiagonal(Cell source, Cell dest) {
        boolean rst = false;
        if (abs(dest.x - source.x) == abs(dest.y - source.y)) {
            rst = true;
        }
        return rst;
    }

    Figure copy(Cell dest);

}
