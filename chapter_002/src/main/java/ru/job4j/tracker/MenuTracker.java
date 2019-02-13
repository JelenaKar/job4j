package ru.job4j.tracker;

public class MenuTracker {
    private Input input;
    private Tracker tracker;

    private static final int ADD = 0;
    private static final int SHOW = 1;
    private static final int EDIT = 2;
    private static final int DELETE = 3;
    private static final int FINDID = 4;
    private static final int FINDNAME = 5;

    private UserAction[] actions = new UserAction[6];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions[ADD] = new CreateItem(ADD, "Добавить новую заявку");
        this.actions[SHOW] = new ShowAllItems(SHOW, "Показать все заявки");
        this.actions[EDIT] = new EditItem(EDIT, "Редактировать заявку");
        this.actions[DELETE] = new DeleteItem(DELETE, "Удалить заявку");
        this.actions[FINDID] = new FindItemById(FINDID, "Найти заявку по id");
        this.actions[FINDNAME] = new FindItemsByName(FINDNAME, "Найти заявки по наименованию");
    }

    public int getActionsLength() {
        return 7;
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    public void show() {
        System.out.println("Меню:");
        for (UserAction action : this.actions) {
            System.out.println(action.info());
        }
        System.out.println("6. Выйти из программы");
    }

    /**
     * Класс, реализующий добавление новой заявки в хранилище.
     */
    private class CreateItem extends BaseAction {

        public CreateItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            String comment = input.ask("Введите комментарий к заявке :");
            Item item = new Item(name, desc, comment);
            tracker.add(item);
            System.out.println("------------ Новая заявка с getId : " + item.getId() + " -----------");
        }
    }

    /**
     * Класс, реализующий выгрузку всех заявок из хранилища.
     */
    private class ShowAllItems extends BaseAction {

        public ShowAllItems(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------- Список всех заявок ---------------");
            Item[] items = tracker.findAll();
            for (Item item : items) {
                System.out.println(item.toString());
            }
            System.out.println("------------- Конец выгрузки из БД -------------");
        }
    }

    /**
     * Класс, реализующий редактирование заявки по id.
     */
    private class EditItem extends BaseAction {

        public EditItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Редактирование заявки --------------");
            String id = input.ask("Введите id заявки :");
            String name = input.ask("Введите новое имя заявки :");
            String desc = input.ask("Введите новое описание заявки :");
            String comment = input.ask("Введите новый комментарий к заявке :");
            Item item = new Item(name, desc, comment);
            boolean result = tracker.replace(id, item);
            if (result) {
                System.out.println("------------ Заявка с getId " + item.getId() + " отредактирована -----------");
            } else {
                System.out.println("------------ Заявка с указанным id отсутствует ------------------");
            }
        }
    }

    /**
     * Класс, реализующий удаление заявки по id.
     */
    private class DeleteItem extends BaseAction {

        public DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление заявки --------------");
            String id = input.ask("Введите id заявки :");
            boolean result = tracker.delete(id);
            if (result) {
                System.out.println("------------ Заявка с getId " + id + " удалена -----------");
            } else {
                System.out.println("------------ Заявка с указанным id отсутствует ------------------");
            }
        }
    }

    /**
     * Класс, реализующий показ заявки по id.
     */
    private class FindItemById extends BaseAction {

        public FindItemById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите id заявки :");
            Item result = tracker.findById(id);
            if (result != null) {
                System.out.println("------------ Подробности по заявке с getId " + id + " -----------");
                System.out.println(result.toString());
            } else {
                System.out.println("------------ Заявка с getId " + id + " не найдена -----------");
            }
        }
    }

    /**
     * Класс, реализующий показ заявок по имени.
     */
    private class FindItemsByName extends BaseAction {

        public FindItemsByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите имя заявки :");
            Item[] items = tracker.findByName(name);
            if (items != null) {
                System.out.println("------------ Список заявок с именем " + name + " -----------");
                for (Item item : items) {
                    System.out.println(item.toString());
                }
            } else {
                System.out.println("------------ Заявок с именем " + name + " не найдено -----------");
            }
        }
    }
}
