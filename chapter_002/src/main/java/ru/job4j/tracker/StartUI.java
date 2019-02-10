package ru.job4j.tracker;

/**
 * Класс - пользовательский интерфейс.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class StartUI {

    private static final String ADD = "0";
    private static final String SHOW = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FINDID = "4";
    private static final String FINDNAME = "5";
    private static final String EXIT = "6";
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
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            switch (answer) {
                case ADD:
                    this.createItem();
                    break;
                case SHOW:
                    this.showAllItems();
                    break;
                case EDIT:
                    this.editItem();
                    break;
                case DELETE:
                    this.deleteItem();
                    break;
                case FINDID:
                    this.findItemById();
                    break;
                case FINDNAME:
                    this.findItemsByName();
                    break;
                case EXIT:
                    exit = true;
                    break;
                default:
            }
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        String comment = this.input.ask("Введите комментарий к заявке :");
        Item item = new Item(name, desc, comment);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + " -----------");
    }

    /**
     * Метод реализует выгрузку всех заявок из хранилища.
     */
    private void showAllItems() {
        System.out.println("------------- Список всех заявок ---------------");
        Item[] items = this.tracker.findAll();
        for (Item item : items) {
            System.out.println(item.toString());
        }
        System.out.println("------------- Конец выгрузки из БД -------------");
    }

    /**
     * Метод реализует редактирование заявки по id.
     */
    private void editItem() {
        System.out.println("------------ Редактирование заявки --------------");
        String id = this.input.ask("Введите id заявки :");
        String name = this.input.ask("Введите новое имя заявки :");
        String desc = this.input.ask("Введите новое описание заявки :");
        String comment = this.input.ask("Введите новый комментарий к заявке :");
        Item item = new Item(name, desc, comment);
        boolean result = this.tracker.replace(id, item);
        if (result) {
            System.out.println("------------ Заявка с getId " + item.getId() + " отредактирована -----------");
        } else {
            System.out.println("------------ Заявка с указанным id отсутствует ------------------");
        }
    }

    /**
     * Метод реализует удаление заявки по id.
     */
    private void deleteItem() {
        System.out.println("------------ Удаление заявки --------------");
        String id = this.input.ask("Введите id заявки :");
        boolean result = this.tracker.delete(id);
        if (result) {
            System.out.println("------------ Заявка с getId " + id + " удалена -----------");
        } else {
            System.out.println("------------ Заявка с указанным id отсутствует ------------------");
        }
    }

    /**
     * Метод реализует показ заявки по id.
     */
    private void findItemById() {
        String id = this.input.ask("Введите id заявки :");
        Item result = this.tracker.findById(id);
        if (result != null) {
            System.out.println("------------ Подробности по заявке с getId " + id + " -----------");
            System.out.println(result.toString());
        } else {
            System.out.println("------------ Заявка с getId " + id + " не найдена -----------");
        }
    }

    /**
     * Метод реализует показ заявок по имени.
     */
    private void findItemsByName() {
        String name = this.input.ask("Введите имя заявки :");
        Item[] items = this.tracker.findByName(name);
        if (items != null) {
            System.out.println("------------ Список заявок с именем " + name + " -----------");
            for (Item item : items) {
                System.out.println(item.toString());
            }
        } else {
            System.out.println("------------ Заявок с именем " + name + " не найдено -----------");
        }
    }

    private void showMenu() {
        System.out.println("Меню:");
        System.out.println("0. Добавить новую заявку");
        System.out.println("1. Показать все заявки");
        System.out.println("2. Редактировать заявку");
        System.out.println("3. Удалить заявку");
        System.out.println("4. Найти заявку по id");
        System.out.println("5. Найти заявки по наименованию");
        System.out.println("6. Выйти из программы");
    }

    /**
     * Запускт программы.
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}