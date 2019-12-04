package ru.job4j.bomberman;

/**
 * Ячейка игровой доски.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public class Cell {
    public int row;
    public int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "Cell{" + "row=" + row + ", col=" + col + "}";
    }
}
