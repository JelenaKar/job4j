package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.assertThat;

public class SimpleArrayListTest {

    private SimpleArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    /**
     * Тестирование получение элемента по корректному индексу.
     */
    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    /**
     * Тестирование получение элемента по некорректному индексу.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenTryToGetElementWithIncorrectIndexThenThrowsNoSuchElementException() {
        list.get(5);
    }

    /**
     * Тестирование добавления элемента.
     */
    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    /**
     * Тестирование удаление элемента из непустого массива.
     */
    @Test
    public void whenDeleteElementThenReceiveItsDataAndFirstElementShiftsRightAndSizeDecreases() {
        Integer deleted = list.get(0);
        Integer newFirst = list.get(1);
        assertThat(list.delete(), is(deleted));
        assertThat(list.get(0), is(newFirst));
        assertThat(list.getSize(), is(2));
    }

    /**
     * Тестирование удаление элемента из пустого массива.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenDeleteElementFromEmptyArrayThenThrowNoSuchElementException() {
        SimpleArrayList<String> empty = new SimpleArrayList<>();
        empty.delete();
    }
}