package ru.job4j.tracker;

import org.junit.Test;
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
     * Тест проверки пункта меню "6 : Выход".
     */
    @Test
    public void when6ThanExits() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"6"});
        new StartUI(input, tracker).init();
    }

}
