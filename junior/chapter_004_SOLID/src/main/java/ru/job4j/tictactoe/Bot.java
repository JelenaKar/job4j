package ru.job4j.tictactoe;

/**
 * Класс бота, делающего случайные ходы.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Bot extends User {

    public Bot(int sign, int size) {
        super(sign, size);
    }

    /**
     * Делает ход в случайную ячейку.
     * @return массив, содержащий координаты строки и столбца, а также фигуру - "крестик" или "нолик".
     */
    @Override
    public int[] move() {
        int x = (int) (Math.random() * size + 1), y = (int) (Math.random() * size + 1);
        return new int[]{x, y, sign};
    }
}
