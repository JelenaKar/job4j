package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
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
public class ContainerListTest {

    private ContainerList<Integer> list;

    @Before
    public void beforeTest() {
        list = new ContainerList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    /**
     * Тестирование автоматического расширения контейнера.
     */
    @Test
    public void whenOverflowLengthThenCapacityGrowsTwice() {
        list.add(17);
        assertThat(list.size(), is(6));
    }

    /**
     * Тестирование получение элемента по корректному индексу.
     */
    @Test
    public void whenGetElementWithCorrectIndexThenReceiveIt() {
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
     * Тестирование поведения итератора при конкурентном изменении коллекции.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenWasChangesThenIteratorThrowsConcurrentModificationException() {
        Iterator iterator = list.iterator();
        iterator.next();
        list.add(17);
        iterator.next();
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
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
    }


}