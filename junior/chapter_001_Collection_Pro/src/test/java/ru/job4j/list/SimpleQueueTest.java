package ru.job4j.list;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleQueueTest {

    /**
     * Тестирование добавления элементов в очередь.
     */
    @Test
    public void whenAddThenLengthGrows() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(3);
        assertThat(queue.size(), is(1));
        queue.push(2);
        assertThat(queue.size(), is(2));
        queue.push(1);
        assertThat(queue.size(), is(3));
    }

    /**
     * Тестирование извлечения элементов из очереди.
     */
    @Test
    public void whenCallPollMethodThenReceiveFirstElementAndQuueSizeDecreases() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertThat(queue.poll(), is(1));
        assertThat(queue.size(), is(2));
        assertThat(queue.poll(), is(2));
        assertThat(queue.size(), is(1));
        assertThat(queue.poll(), is(3));
        assertThat(queue.size(), is(0));
    }

    /**
     * Тестирование извлечения элементов из пустой очереди.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenPollEmptyQueueThenThrowNoSuchElementException() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.poll();
    }

}