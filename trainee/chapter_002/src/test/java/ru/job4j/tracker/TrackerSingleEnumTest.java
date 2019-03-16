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
public class TrackerSingleEnumTest {

    /**
     * Проверка того, что повторное создание класса Tracker возвращают один и тот же объект.
     */
    @Test
    public void whenCreatedTwoObjectsAreEqual() {
        TrackerSingleEnum tracker1 = TrackerSingleEnum.INSTANCE;
        TrackerSingleEnum tracker2 = TrackerSingleEnum.INSTANCE;
        assertThat(tracker1 == tracker2, is(true));
    }

    /**
     * Тест добавления новой заявки в хранилище из TrackerTest.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        TrackerSingleEnum tracker = TrackerSingleEnum.INSTANCE;
        Item item = new Item("test1", "TestDescription", "TestComment");
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }
}
