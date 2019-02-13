package ru.job4j.tracker;

import java.util.List;
import java.util.Scanner;

/**
 * Класс, обеспечивающий ввод данных с клавиатуры.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Выводит на экран вопрос, принимает введённый ответ.
     * @param ask выводимый на экран вопрос.
     * @return введённый пользователем ответ.
     */
    public String ask(String ask) {
        System.out.print(ask);
        return this.scanner.nextLine();
    }

    /**
     * Выводит на экран вопрос, принимает введённый ответ.
     * @param ask выводимый на экран вопрос.
     * @param range диапазон возможных пунктов меню.
     * @return введённый пользователем ответ.
     */
    public int ask(String ask, List<Integer> range) {
        int key = Integer.valueOf(this.ask(ask));
        boolean exists = false;
        for (int value : range) {
            if (value == key) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            throw new MenuOutException("Input value is out of bounds.");
        } else {
            return key;
        }
    }
}
