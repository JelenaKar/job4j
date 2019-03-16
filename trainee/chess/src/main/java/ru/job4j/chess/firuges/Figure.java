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

   default boolean isHorizontal(Cell source, Cell dest) {
        boolean rst = false;
        if (abs(dest.x - source.x) != 0 && (dest.y - source.y) == 0) {
            rst = true;
        }
        return rst;
   }

    default boolean isVertical(Cell source, Cell dest) {
        boolean rst = false;
        if ((dest.x - source.x) == 0 && abs(dest.y - source.y) != 0) {
            rst = true;
        }
        return rst;
    }

    Figure copy(Cell dest);

}
