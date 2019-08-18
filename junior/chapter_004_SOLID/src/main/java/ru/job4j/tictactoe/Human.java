package ru.job4j.tictactoe;

/**
 * Класс живого пользователя.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Human extends User {

    private NumberInput in;

    public Human(int sign, int size, NumberInput in) {
        super(sign, size);
        this.in = in;
    }

    /**
     * Делает ход в выбранную пользователем ячейку.
     * @return массив, содержащий координаты строки и столбца, а также фигуру - "крестик" или "нолик".
     */
    @Override
    public int[] move() {
        int[] res = new int[3];
        do {
            res[0] = in.askNumber("Введите номер строки: ", 1, size);
        } while (res[0] == -1);
        do {
            res[1] = in.askNumber("Введите номер столбца: ", 1, size);
        } while (res[1] == -1);
        res[2] = this.sign;
        return res;
    }
}