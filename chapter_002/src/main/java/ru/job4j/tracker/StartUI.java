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
    private final Output output;
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker, Output output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
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
                    this.output.print(this.showAllItems());
                    break;
                case EDIT:
                    this.editItem();
                    break;
                case DELETE:
                    this.deleteItem();
                    break;
                case FINDID:
                    this.output.print(this.findItemById());
                    break;
                case FINDNAME:
                    this.output.print(this.findItemsByName());
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
    private String showAllItems() {
        StringBuilder result = new StringBuilder();
        result.append("------------- Список всех заявок ---------------\n");
        Item[] items = this.tracker.findAll();
        for (Item item : items) {
            result.append(item.toString()).append(System.lineSeparator());
        }
        result.append("------------- Конец выгрузки из БД -------------");
        return result.toString();
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
    private String findItemById() {
        System.out.println("------------ Поиск заявки --------------");
        String id = this.input.ask("Введите id заявки :");
        Item result = this.tracker.findById(id);
        StringBuilder stringResult = new StringBuilder();
        if (result != null) {
            stringResult.append("------------ Подробности по заявке с getId ").append(id).append(" -----------\n")
                        .append(result.toString()).append(System.lineSeparator());
        } else {
            stringResult.append("------------ Заявка с getId ").append(id).append(" не найдена -----------\n");
        }
        return stringResult.toString();
    }

    /**
     * Метод реализует показ заявок по имени.
     */
    private String findItemsByName() {
        System.out.println("------------ Поиск заявок --------------");
        String name = this.input.ask("Введите имя заявки :");
        Item[] items = this.tracker.findByName(name);
        System.out.println(items != null);
        StringBuilder stringResult = new StringBuilder();
        if (items != null) {
            stringResult.append("------------ Список заявок с именем ").append(name).append(" -----------\n");
            for (Item item : items) {
                stringResult.append(item.toString()).append(System.lineSeparator());
            }
        } else {
            stringResult.append("------------ Заявок с именем ").append(name).append(" не найдено -----------\n");
        }
        return stringResult.toString();
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
        new StartUI(new ConsoleInput(), new Tracker(), new ConsoleOutput()).init();
    }
}