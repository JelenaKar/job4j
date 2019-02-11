package ru.job4j.tracker;

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
        int key;
        menu.fillActions();
        do {
            menu.show();
            try {
                key = Integer.valueOf(this.input.ask("Введите пункт меню : "));
                if (key == 6) {
                    break;
                }
                menu.select(key);
            } catch (Exception e) {
                continue;
            }

        } while (true);
    }

    /**
     * Запускт программы.
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}