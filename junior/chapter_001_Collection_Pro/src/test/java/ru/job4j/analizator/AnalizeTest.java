package ru.job4j.analizator;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizeTest {

    private Analize analize;
    List<Analize.User> previous;

    @Before
    public void beforeTest() {
        analize = new Analize();
        previous = List.of(new Analize.User(1, "Ivanov"),
                new Analize.User(2, "Petrov"), new Analize.User(3, "Sidorov"));
    }

    /**
     * Тестирование ситуации, когда коллекция не изменилась.
     */
    @Test
    public void whenNoChangesThenCountersEqualsZero() {
        List<Analize.User> current = new ArrayList<>(previous);
        assertThat(analize.diff(previous, current).added, is(0));
        assertThat(analize.diff(previous, current).changed, is(0));
        assertThat(analize.diff(previous, current).deleted, is(0));
    }

    /**
     * Тестирование добавления объектов.
     */
    @Test
    public void whenAddTwoUsersThenCounterEqualsTwo() {
        List<Analize.User> current = new ArrayList<>(previous);
        current.addAll(List.of(
                new Analize.User(25, "Jakovljev"),
                new Analize.User(105, "Volkov"))
        );
        assertThat(analize.diff(previous, current).added, is(2));
        assertThat(analize.diff(previous, current).changed, is(0));
        assertThat(analize.diff(previous, current).deleted, is(0));
    }

    /**
     * Тестирование изменения объектов.
     */
    @Test
    public void whenChangeTwoElementsThenCounterEqualsTwo() {
        List<Analize.User> current = new ArrayList<>(previous);
        current.set(1, new Analize.User(2, "Kotov"));
        current.set(2, new Analize.User(3, "Zajcev"));
        assertThat(analize.diff(previous, current).added, is(0));
        assertThat(analize.diff(previous, current).changed, is(2));
        assertThat(analize.diff(previous, current).deleted, is(0));
    }

    /**
     * Тестирование удаление объектов.
     */
    @Test
    public void whenDeleteElementThenCounterEqualsOne() {
        List<Analize.User> current = new ArrayList<>(previous);
        current.remove(1);
        assertThat(analize.diff(previous, current).added, is(0));
        assertThat(analize.diff(previous, current).changed, is(0));
        assertThat(analize.diff(previous, current).deleted, is(1));
    }

    /**
     * Тестирование удаление объектов.
     */
    @Test
    public void whenAddChangeDeleteAtTheSameTime() {
        List<Analize.User> current = new ArrayList<>(previous);
        current.remove(1);
        current.add(new Analize.User(25, "Jakovljev"));
        current.set(1, new Analize.User(3, "Zajcev"));
        assertThat(analize.diff(previous, current).added, is(1));
        assertThat(analize.diff(previous, current).changed, is(1));
        assertThat(analize.diff(previous, current).deleted, is(1));
    }
}