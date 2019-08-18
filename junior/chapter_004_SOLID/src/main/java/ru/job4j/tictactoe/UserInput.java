package ru.job4j.tictactoe;

import java.util.Scanner;

/**
 * Класс пользовательского ввода.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class UserInput implements SimpleInput {

    private Scanner in = new Scanner(System.in);

    @Override
    public int askNumber(String question, int min, int max) {
        System.out.print(question);
        int res = -1;
        try {
            res = Integer.valueOf(in.next());
            if ((res < min) || (res > max)) {
                System.out.printf("Значение должно быть в диапазоне от %d до %d%s",
                        min, max, System.lineSeparator());
                res = -1;
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Значение не является корректным целым числом!");
        }
        return res;
    }

    @Override
    public String ask(String question) {
        System.out.print(question);
        return in.next();
    }
}
