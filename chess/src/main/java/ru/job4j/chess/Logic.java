package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

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
        if (index != -1) {
            Cell[] steps = this.figures[index].way(source, dest);
            if (!isOccupied(steps)) {
                if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                    rst = true;
                    this.figures[index] = this.figures[index].copy(dest);
                }
            } else {
                throw new OccupiedWayException("The way of the figure is occupied!");
            }
            return rst;
        } else {
            throw new FigureNotFoundException("Figure not found!");
        }
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    private boolean isOccupied(Cell[] steps) {
        boolean rst = false;
        for (Cell step : steps) {
            if (this.findBy(step) != -1) {
                rst = true;
                break;
            }
        }
        return rst;
    }
}
