package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс - пользовательский интерфейс.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class StartUI {

    private final Input input;
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        List<Integer> range = new ArrayList<>();
        menu.fillActions();
        for (int i = 0; i < menu.getActionsLength(); i++) {
            range.add(i);
        }
        menu.fillActions();
        do {
            menu.show();
            int key = this.input.ask("Введите пункт меню : ", range);
            if (key == 6) {
                break;
            }
            menu.select(key);

        } while (true);
    }

    /**
     * Запускт программы.
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}