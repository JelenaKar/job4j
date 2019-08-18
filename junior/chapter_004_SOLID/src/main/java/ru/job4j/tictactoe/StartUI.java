package ru.job4j.tictactoe;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Класс - точка входа.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class StartUI {

    public void start(SimpleInput in) throws IOException {
        int size;
        int combination;
        do {
            size = in.askNumber("Введите величину поля (от 3 до 10): ", 3, 10);
        } while (size == -1);

        do {
            combination = in.askNumber(String.format("Введите длину победной комбинации (от 3 до %d): ", size), 3, size);
        } while (combination == -1);

        int sign = Square.ZERO;
        if ("y".equals(in.ask("Желаете ходить первым? (y/n): "))) {
            sign = Square.CROSS;
        }

        User rival;
        if ("y".equals(in.ask("Желаете играть с ботом? (y/n): "))) {
            rival = new Bot(-sign, size);
        } else {
            rival = new Human(-sign, size, in);
        }

        Map<Integer, User> players = new TreeMap<>(Map.of(sign, new Human(sign, size, in), -sign, rival));
        Logic play = new Logic(players, size, combination);
        play.play();
    }

    public static void main(String[] args) throws IOException {
      UserInput in = new UserInput();
       new StartUI().start(in);
    }

}
