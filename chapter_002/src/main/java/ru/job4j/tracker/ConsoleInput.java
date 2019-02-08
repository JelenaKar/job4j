package ru.job4j.tracker;

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
}
