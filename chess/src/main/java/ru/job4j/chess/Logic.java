package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Класс игровой логики.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        int index = this.findBy(source);
        if (index == -1) {
            throw new FigureNotFoundException("Figure is not found!");
        }

        Cell[] steps = this.figures[index].way(source, dest);

        if (isOccupied(steps)) {
            throw new OccupiedWayException("The way of the figure is occupied!");
        }
        if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
            rst = true;
            this.figures[index] = this.figures[index].copy(dest);
        }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        return IntStream
                .range(0, this.figures.length)
                .filter(i -> this.figures[i] != null && this.figures[i].position().equals(cell))
                .findFirst()
                .orElse(-1);
    }

    private boolean isOccupied(Cell[] steps) {
        return Arrays.stream(steps)
                .map(step -> this.findBy(step) != -1).findAny().orElse(false);
    }
}
