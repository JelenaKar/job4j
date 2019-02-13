package ru.job4j.tracker;

import org.junit.Test;

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
        Tracker tracker1 = TrackerSingleStaticFinalClass.getInstance();
        Tracker tracker2 = TrackerSingleStaticFinalClass.getInstance();
        assertThat(tracker1 == tracker2, is(true));
    }

    /**
     * Тест выгрузки всех строк из хранилища из TrackerTest.
     */
    @Test
    public void whenFindAllThenReturnAllItemsWithoutNull() {
        Tracker testTracker = TrackerSingleStaticFinalClass.getInstance();
        Item[] expected = new Item[3];

        Item firstItem = new Item("test1", "testDescription", "TestComment");
        testTracker.add(firstItem);
        expected[0] = firstItem;

        Item secondItem = new Item("test2", "testDescription2", "TestComment2");
        testTracker.add(secondItem);
        expected[1] = secondItem;

        Item thirdItem = new Item("test3", "testDescription3", "TestComment3");
        testTracker.add(thirdItem);
        expected[2] = thirdItem;

        Item[] result = testTracker.findAll();
        assertThat(result, is(expected));
    }
}
