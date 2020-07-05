package ru.job4j.cinema;

import java.util.List;

/**
 * Объект зала.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Hall {
    private List<List<Place>> matrix;
    private int width;
    private int height;

    public Hall(List<Place> places) {
        this.matrix = Helpers.listToMatrix(places);
        this.width = Helpers.getMatrixWidth(this.matrix);
        this.height = this.matrix.size();
    }

    public List<List<Place>> getMatrix() {
        return matrix;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}