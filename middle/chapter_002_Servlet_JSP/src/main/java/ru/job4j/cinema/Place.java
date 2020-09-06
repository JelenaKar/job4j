package ru.job4j.cinema;

/**
 * Класс посадочного места в зале.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Place {
    private int row;
    private int col;
    private boolean isFree;

    public Place(int row, int col, boolean isFree, double price) {
        this.row = row;
        this.col = col;
        this.isFree = isFree;
        this.price = price;
    }

    private double price;

    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean isfree) {
        isFree = isfree;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
