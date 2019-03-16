package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Рисование двумерной пирамиды в псевдографике.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Paint {

    /**
     * Рисует пирамиду заданной высоты.
     * @param height высота доски.
     * @return изображение пирамиды заданной высоты.
     */
    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }

    /**
     * Рисует правую полупирамиду заданной высоты.
     * @param height высота пирамиды.
     * @return изображение правой полупирамиды заданной высоты.
     */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }

    /**
     * Рисует левую полупирамиду заданной высоты.
     * @param height высота пирамиды.
     * @return изображение левой полупирамиды заданной высоты.
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }

    private String loopBy(int height, int width, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != width; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}