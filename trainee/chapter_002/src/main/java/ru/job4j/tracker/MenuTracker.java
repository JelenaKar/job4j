package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private final Consumer<String> output;

    private static final int ADD = 0;
    private static final int SHOW = 1;
    private static final int EDIT = 2;
    private static final int DELETE = 3;
    private static final int FINDID = 4;
    private static final int FINDNAME = 5;

    private HashMap<Integer, BaseAction> actions = new HashMap<>();

    public MenuTracker(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    public void fillActions() {
        this.actions.put(ADD, new CreateItem(ADD, "Добавить новую заявку"));
        this.actions.put(SHOW, new ShowAllItems(SHOW, "Показать все заявки"));
        this.actions.put(EDIT, new EditItem(EDIT, "Редактировать заявку"));
        this.actions.put(DELETE, new DeleteItem(DELETE, "Удалить заявку"));
        this.actions.put(FINDID, new FindItemById(FINDID, "Найти заявку по id"));
        this.actions.put(FINDNAME, new FindItemsByName(FINDNAME, "Найти заявки по наименованию"));
    }

    public int getActionsLength() {
        return this.actions.size() + 1;
    }

    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    public void show() {
        this.output.accept("Меню:");
        this.actions.values().forEach(action -> this.output.accept(action.info()));
        this.output.accept("6. Выйти из программы");
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
            output.accept("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            String comment = input.ask("Введите комментарий к заявке :");
            Item item = new Item(name, desc, comment);
            tracker.add(item);
            output.accept("------------ Новая заявка с getId : " + item.getId() + " -----------");
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
            output.accept("------------- Список всех заявок ---------------");
            tracker.findAll().forEach(item -> output.accept(item.toString()));
            output.accept("------------- Конец выгрузки из БД -------------");
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
            output.accept("------------ Редактирование заявки --------------");
            String id = input.ask("Введите id заявки :");
            String name = input.ask("Введите новое имя заявки :");
            String desc = input.ask("Введите новое описание заявки :");
            String comment = input.ask("Введите новый комментарий к заявке :");
            Item item = new Item(name, desc, comment);
            boolean result = tracker.replace(id, item);
            if (result) {
                output.accept("------------ Заявка с getId " + item.getId() + " отредактирована -----------");
            } else {
                output.accept("------------ Заявка с указанным id отсутствует ------------------");
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
            output.accept("------------ Удаление заявки --------------");
            String id = input.ask("Введите id заявки :");
            boolean result = tracker.delete(id);
            if (result) {
                output.accept("------------ Заявка с getId " + id + " удалена -----------");
            } else {
                output.accept("------------ Заявка с указанным id отсутствует ------------------");
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
                output.accept("------------ Подробности по заявке с getId " + id + " -----------");
                output.accept(result.toString());
            } else {
                output.accept("------------ Заявка с getId " + id + " не найдена -----------");
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
            ArrayList<Item> items = tracker.findByName(name);
            if (items != null) {
                output.accept("------------ Список заявок с именем " + name + " -----------");
                items.forEach(item -> output.accept(item.toString()));
            } else {
                output.accept("------------ Заявок с именем " + name + " не найдено -----------");
            }
        }
    }
}