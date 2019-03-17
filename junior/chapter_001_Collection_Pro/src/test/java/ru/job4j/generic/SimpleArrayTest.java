package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleArrayTest {

    SimpleArray<String> intArr;

    @Before
    public void setUp() {
        intArr = new SimpleArray<>(8);
        intArr.add("каждый");
        intArr.add("охотник");
        intArr.add("желает");
        intArr.add("знать");
        intArr.add("где");
        intArr.add("сидит");
        intArr.add("фазан");
    }

    /**
     * Тестирование добавления элемента, когда имеются свободные ячейки.
     */
    @Test
    public void whenAddNewElementThenLengthGrows() {
        intArr.add("радуга");
        assertThat(intArr.size(), is(8));
    }

    /**
     * Тестирование добавления элемента, когда свободные ячейки отсутствуют.
     */
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddInOverflowingArray() {
        intArr.add("радуга");
        intArr.add("лишок");
    }

    /**
     * Тестирование получение элемента по корректному индексу.
     */
    @Test
    public void whenGetElementWithCorrectIndexThenFindIt() {
        assertThat(intArr.get(1), is("охотник"));
    }

    /**
     * Тестирование получение элемента по некорректному индексу.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenTryToGetElementWithIncorrectIndexThenThrowsNoSuchElementException() {
        intArr.get(8);
    }

    /**
     * Тестирование замены элемента по корректному индексу.
     */
    @Test
    public void whenSetElementWithCorrectIndexThenReplaceIt() {
        intArr.set(3, "найти");
        assertThat(intArr.get(3), is("найти"));
    }

    /**
     * Тестирование замены элемента по некорректному индексу.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenTryToSetElementWithIncorrectIndexThenThrowsNoSuchElementException() {
        intArr.set(8, "радуга");
    }

    /**
     * Тестирование удаления элемента по корректному индексу.
     */
    @Test
    public void whenRemoveElementWithCorrectIndexThenNextShiftLeft() {
        intArr.remove(3);
        assertThat(intArr.get(3), is("где"));
    }

    /**
     * Тестирование удаления элемента по некорректному индексу.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenTryToRemoveElementWithIncorrectIndexThenThrowsNoSuchElementException() {
        intArr.remove(8);
    }

}