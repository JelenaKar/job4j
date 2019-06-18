package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class TrackerSingleStaticFinalClassTest {

    /**
     * Проверка того, что повторное создание класса Tracker возвращают один и тот же объект.
     */
    @Test
    public void whenCreatedTwoObjectsAreEqual() {
        ITracker tracker1 = TrackerSingleStaticFinalClass.getInstance();
        ITracker tracker2 = TrackerSingleStaticFinalClass.getInstance();
        assertThat(tracker1 == tracker2, is(true));
    }

    /**
     * Тест выгрузки всех строк из хранилища из TrackerTest.
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
}
