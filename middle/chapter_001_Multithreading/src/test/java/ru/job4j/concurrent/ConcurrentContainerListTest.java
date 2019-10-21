package ru.job4j.concurrent;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
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
public class ConcurrentContainerListTest {

    private ConcurrentContainerList<Integer> list;

    @Before
    public void beforeTest() throws InterruptedException {
        list = new ConcurrentContainerList<>(3);
        Thread thread1 = new Thread(
                () -> {
                    list.add(17);
                    list.add(22);
                }
        );
        Thread thread2 = new Thread(
                () -> list.add(11)
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    /**
     * Тестирование автоматического расширения контейнера.
     */
    @Test
    public void whenOverflowLengthThenCapacityGrowsTwice() throws InterruptedException {
        Thread thread3 = new Thread(
                () -> list.add(3)
        );
        thread3.start();
        thread3.join();
        assertThat(list.size(), is(6));
    }

    /**
     * Тестирование получение элемента по корректному индексу.
     */
    @Test
    public void whenGetElementWithCorrectIndexThenReceiveInteger() {
        assertTrue(list.get(1) instanceof Integer);
    }

    /**
     * Тестирование получение элемента по некорректному индексу.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenTryToGetElementWithIncorrectIndexThenThrowsNoSuchElementException() {
        list.get(5);
    }

    /**
     * Тестирование поведения итератора при конкурентном изменении коллекции.
     */
    @Test
    public void whenWasChangesThenIteratorDoesNotSeeThisChangeAndWorksNormally() {
        Iterator iterator = list.iterator();
        iterator.next();
        iterator.next();
        list.add(5);
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }

    /**
     * Тестирование поведения итератора при выходе за пределы контейнера.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenIteratorOnTheLastElementThenNextThrowsNoSuchException() {
        Iterator iterator = list.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    /**
     * Тестирование независимости двух методов итератора (next и hasNext).
     */
    @Test
    public void testsThatNextMethodDoesntDependsOnHasNextMethodInvocation() {
        Iterator iterator = list.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertTrue(iterator.next() instanceof Integer);
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertTrue(iterator.next() instanceof Integer);
        assertThat(iterator.hasNext(), is(true));
        assertTrue(iterator.next() instanceof Integer);
        assertThat(iterator.hasNext(), is(false));
    }
}