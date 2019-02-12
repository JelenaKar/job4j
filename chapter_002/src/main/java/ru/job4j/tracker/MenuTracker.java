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
        this.actions[ADD] = new CreateItem();
        this.actions[SHOW] = new ShowAllItems();
        this.actions[EDIT] = new EditItem();
        this.actions[DELETE] = new DeleteItem();
        this.actions[FINDID] = new FindItemById();
        this.actions[FINDNAME] = new FindItemsByName();
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
    private class CreateItem implements UserAction {
        @Override
        public  int key() {
            return MenuTracker.ADD;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Добавить новую заявку");
        }
    }

    /**
     * Класс, реализующий выгрузку всех заявок из хранилища.
     */
    private class ShowAllItems implements UserAction {
        @Override
        public  int key() {
            return MenuTracker.SHOW;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Показать все заявки");
        }
    }

    /**
     * Класс, реализующий редактирование заявки по id.
     */
    private class EditItem implements UserAction {
        @Override
        public  int key() {
            return MenuTracker.EDIT;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Редактировать заявку");
        }
    }

    /**
     * Класс, реализующий удаление заявки по id.
     */
    private class DeleteItem implements UserAction {
        @Override
        public  int key() {
            return MenuTracker.DELETE;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Удалить заявку");
        }
    }

    /**
     * Класс, реализующий показ заявки по id.
     */
    private class FindItemById implements UserAction {
        @Override
        public  int key() {
            return MenuTracker.FINDID;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Найти заявку по id");
        }
    }

    /**
     * Класс, реализующий показ заявок по имени.
     */
    private class FindItemsByName implements UserAction {
        @Override
        public  int key() {
            return MenuTracker.FINDNAME;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Найти заявки по наименованию");
        }
    }
}
