package ru.job4j.list;

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
public class SimpleStackTest {

    private SimpleStack<Integer> stack = new SimpleStack<>();

    /**
     * Тестирование добавления элементов в стек.
     */
    @Test
    public void whenAddThenLengthGrows() {
        stack.push(3);
        assertThat(stack.size(), is(1));
        stack.push(2);
        assertThat(stack.size(), is(2));
        stack.push(1);
        assertThat(stack.size(), is(3));
    }

    /**
     * Тестирование удаления элементов из стека.
     */
    @Test
    public void whenCallPollMethodThenReceiveLastElementAndStackSizeDecreases() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.poll(), is(3));
        assertThat(stack.size(), is(2));
        assertThat(stack.poll(), is(2));
        assertThat(stack.size(), is(1));
        assertThat(stack.poll(), is(1));
        assertThat(stack.size(), is(0));
    }

    /**
     * Тестирование удаления элементов из стека.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenPollEmptyStackThenThrowNoSuchElementException() {
        stack.poll();
    }

}