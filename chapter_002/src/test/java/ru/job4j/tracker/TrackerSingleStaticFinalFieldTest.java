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
public class TrackerSingleStaticFinalFieldTest {

    /**
     * Проверка того, что повторное создание класса Tracker возвращают один и тот же объект.
     */
    @Test
    public void whenCreatedTwoObjectsAreEqual() {
        Tracker tracker1 = TrackerSingleStaticFinalField.getInstance();
        Tracker tracker2 = TrackerSingleStaticFinalField.getInstance();
        assertThat(tracker1 == tracker2, is(true));
    }

    /**
     * Тест удаления заявки с заданным id из TrackerTest.
     */
    @Test
    public void whenDeleteItemThenReturnWithoutIt() {
        Tracker testTracker = TrackerSingleStaticFinalField.getInstance();
        Tracker resultTracker = TrackerSingleStaticFinalField.getInstance();

        Item firstItem = new Item("test1", "testDescription", "TestComment");
        testTracker.add(firstItem);
        resultTracker.add(firstItem);

        Item secondItem = new Item("test2", "testDescription2", "TestComment2");
        testTracker.add(secondItem);

        Item thirdItem = new Item("test3", "testDescription3", "TestComment3");
        testTracker.add(thirdItem);
        resultTracker.add(thirdItem);

        testTracker.delete(secondItem.getId());
        assertThat(testTracker, is(resultTracker));
    }
}
