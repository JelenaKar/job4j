package ru.job4j.condition;

import static java.lang.Math.*;

/**
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Point {
    private int x;
    private int y;

    /**
     * Конструктор - создаёт точку с заданными координатами.
     * @param x координата x.
     * @param y координата y.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Конструктор - создаёт точку с заданными координатами.
     * @param that произвольная точка класса Point, расстояние до которой необходимо рассчитать.
     * @return Расстояние между заданной и произольной точками.
     */
    public double distanceTo(Point that) {
        return sqrt(
                pow(this.x - that.x, 2) + pow(this.y - that.y, 2)
        );
    }
}