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
public class TrackerSingleStaticFieldTest {

    /**
     * Проверка того, что повторное создание класса Tracker возвращают один и тот же объект.
     */
    @Test
    public void whenCreatedTwoObjectsAreEqual() {
        Tracker tracker1 = TrackerSingleStaticField.getInstance();
        Tracker tracker2 = TrackerSingleStaticField.getInstance();
        assertThat(tracker1 == tracker2, is(true));
    }

    /**
     * Тест замены заявки из TrackerTest.
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = TrackerSingleStaticField.getInstance();
        Item previous = new Item("test1", "testDescription", "TestComment");
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", "TestComment2");
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }
}
