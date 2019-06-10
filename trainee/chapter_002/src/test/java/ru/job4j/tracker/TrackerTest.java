package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class TrackerTest {

    /**
     * Тест добавления новой заявки в хранилище.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        ITracker tracker = new Tracker();
        Item item = new Item("test1", "TestDescription", "TestComment");
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
    }

    /**
     * Тест замены заявки.
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        ITracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", "TestComment");
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", "TestComment2");
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    /**
     * Тест удаления заявки с заданным id.
     */
    @Test
    public void whenDeleteItemThenReturnWithoutIt() {
        ITracker testTracker = new Tracker();
        ITracker resultTracker = new Tracker();

        Item firstItem = new Item("test1", "testDescription", "TestComment");
        testTracker.add(firstItem);
        resultTracker.add(firstItem);

        Item secondItem = new Item("test2", "testDescription2", "TestComment2");
        testTracker.add(secondItem);

        Item thirdItem = new Item("test3", "testDescription3", "TestComment3");
        testTracker.add(thirdItem);
        resultTracker.add(thirdItem);

        testTracker.delete(secondItem.getId());
        Item expected = null;
        assertThat(testTracker.findById(secondItem.getId()), is(expected));
    }

    /**
     * Тест выгрузки всех строк из хранилища.
     */
    @Test
    public void whenFindAllThenReturnAllItemsWithoutNull() {
        ITracker testTracker = new Tracker();
        ArrayList<Item> expected = new ArrayList<>();

        Item firstItem = new Item("test1", "testDescription", "TestComment");
        testTracker.add(firstItem);
        expected.add(firstItem);

        Item secondItem = new Item("test2", "testDescription2", "TestComment2");
        testTracker.add(secondItem);
        expected.add(secondItem);

        Item thirdItem = new Item("test3", "testDescription3", "TestComment3");
        testTracker.add(thirdItem);
        expected.add(thirdItem);

        List<Item> result = testTracker.findAll();
        assertThat(result, is(expected));
    }

    /**
     * Тест выгрузки заявок с заданным именем, когда имя существует.
     */
    @Test
    public void whenFindByNameThenReturnMatchItems() {
        ITracker testTracker = new Tracker();
        ArrayList<Item> expected = new ArrayList<>();

        Item firstItem = new Item("test1", "testDescription", "TestComment");
        testTracker.add(firstItem);

        Item secondItem = new Item("test2", "testDescription2", "TestComment2");
        testTracker.add(secondItem);
        expected.add(secondItem);

        Item thirdItem = new Item("test2", "testDescription3", "TestComment3");
        testTracker.add(thirdItem);
        expected.add(thirdItem);

        assertThat(testTracker.findByName("test2"), is(expected));
    }

    /**
     * Тест выгрузки заявок с заданным именем, когда имени не существует.
     */
    @Test
    public void whenNotFindByNameThenReturnNull() {
        ITracker testTracker = new Tracker();
        Item[] expected = null;

        Item firstItem = new Item("test1", "testDescription", "TestComment");
        testTracker.add(firstItem);

        Item secondItem = new Item("test2", "testDescription2", "TestComment2");
        testTracker.add(secondItem);

        Item thirdItem = new Item("test2", "testDescription3", "TestComment3");
        testTracker.add(thirdItem);

        assertThat(testTracker.findByName("test5"), is(expected));
    }

    /**
     * Тест выгрузки заявок с заданным id, когда id существует.
     */
    @Test
    public void whenFindByIdThenReturnMatchItem() {
        ITracker testTracker = new Tracker();
        Item expected;

        Item firstItem = new Item("test1", "testDescription", "TestComment");
        testTracker.add(firstItem);

        Item secondItem = new Item("test2", "testDescription2", "TestComment2");
        testTracker.add(secondItem);
        expected = secondItem;

        Item thirdItem = new Item("test3", "testDescription3", "TestComment3");
        testTracker.add(thirdItem);

        Item result = testTracker.findById(secondItem.getId());
        assertThat(result, is(expected));
    }

    /**
     * Тест выгрузки заявок с заданным id, когда id не существует.
     */
    @Test
    public void whenNotFindByIdThenReturnNull() {
        ITracker testTracker = new Tracker();
        Item expected = null;

        Item firstItem = new Item("test1", "testDescription", "TestComment");
        testTracker.add(firstItem);

        Item secondItem = new Item("test2", "testDescription2", "TestComment2");
        testTracker.add(secondItem);

        Item thirdItem = new Item("test3", "testDescription3", "TestComment3");
        testTracker.add(thirdItem);

        Item result = testTracker.findById("test");
        assertThat(result, is(expected));
    }

}