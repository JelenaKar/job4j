package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class StartUITest {

    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    private final String menu = "Меню:\n0. Добавить новую заявку\n1. Показать все заявки\n2. Редактировать заявку\n"
            + "3. Удалить заявку\n4. Найти заявку по id\n5. Найти заявки по наименованию\n6. Выйти из программы";

    /**
     * Тест проверки пункта меню "0 : Добавить новую заявку".
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "comment", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    /**
     * Тест проверки пункта меню "1 : Показать все заявки".
     */
    @Test
    public void whenUserListAllItems() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("test name1", "desc1", "comment1"));
        tracker.add(new Item("test name2", "desc2", "comment2"));

        Input input = new StubInput(new String[]{"1", "6"});

        this.loadOutput();
        new StartUI(input, tracker).init();

        StringBuilder result = new StringBuilder();
        Item[] items = tracker.findAll();
        result.append(menu).append("\n------------- Список всех заявок ---------------\n");
        for (Item item : items) {
            result.append(item.toString()).append(System.lineSeparator());
        }
        result.append("------------- Конец выгрузки из БД -------------\n").append(menu)
                .append(System.lineSeparator());

        assertThat(
                this.out.toString(),
                is(result.toString())
        );
    }

    /**
     * Тест проверки пункта меню "2 : Редактировать заявку".
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc", "comment"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "измененной заявкой", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    /**
     * Тест проверки пункта меню "3 : Удалить заявку".
     */
    @Test
    public void whenDeleteThenTrackerDoesNotFindItemById() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc", "comment"));
        Input input = new StubInput(new String[]{"3", item.getId(), "6"});
        new StartUI(input, tracker).init();
        Item expected = null;
        assertThat(tracker.findById(item.getId()), is(expected));
    }

    /**
     * Тест проверки пункта меню "4 : Найти заявку по id".
     */
    @Test
    public void whenUserFindById() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("test name1", "desc1", "comment1"));
        Item expectedItem = tracker.add(new Item("test name2", "desc2", "comment2"));

        Input input = new StubInput(new String[]{"4", expectedItem.getId(), "6"});
        this.loadOutput();
        new StartUI(input, tracker).init();

        StringBuilder result = new StringBuilder();

        result.append(menu).append("\n------------ Подробности по заявке с getId ")
                .append(expectedItem.getId()).append(" -----------\n")
                .append(expectedItem.toString()).append(System.lineSeparator())
                .append(menu).append(System.lineSeparator());

        assertThat(
                this.out.toString(),
                is(result.toString())
        );
    }

    /**
     * Тест проверки пункта меню "5 : Найти заявки по именам".
     */
    @Test
    public void whenUserFindByName() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("test name1", "desc1", "comment1"));
        tracker.add(new Item("test name2", "desc2", "comment2"));
        tracker.add(new Item("test name2", "desc3", "comment3"));

        String findName = "test name2";

        Input input = new StubInput(new String[]{"5", findName, "6"});
        this.loadOutput();
        new StartUI(input, tracker).init();

        StringBuilder result = new StringBuilder();
        Item[] items = tracker.findByName(findName);

        result.append(menu).append("\n------------ Список заявок с именем ")
                .append(findName).append(" -----------\n");
        for (Item item : items) {
            result.append(item.toString()).append(System.lineSeparator());
        }
        result.append(menu).append(System.lineSeparator());

        assertThat(
               this.out.toString(),
                is(result.toString())
        );
    }

    /**
     * Тест проверки пункта меню "6 : Выход".
     */
    @Test
    public void when6ThanExits() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"6"});
        new StartUI(input, tracker).init();
    }

}
