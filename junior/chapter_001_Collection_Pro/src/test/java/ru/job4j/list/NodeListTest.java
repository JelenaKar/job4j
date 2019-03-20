package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
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
public class NodeListTest {

    private NodeList<Integer> list;

    @Before
    public void beforeTest() {
        list = new NodeList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(11);
        list.add(17);
        list.add(22);
    }

    /**
     * Тестирование получение элементов, расположенных в первой половине в произвольном порядке.
     */
    @Test
    public void whenGetElementWithIndexBeforeCenterThenReceiveIt() {
        assertThat(list.get(2), is(3));
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
    }

    /**
     * Тестирование получение элементов, расположенных во второй половине в произвольном порядке.
     */
    @Test
    public void whenGetElementWithIndexAfterCenterThenReceiveIt() {
        assertThat(list.get(5), is(22));
        assertThat(list.get(3), is(11));
        assertThat(list.get(4), is(17));
    }

    /**
     * Тестирование получение элемента по некорректному индексу.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenTryToGetElementWithIncorrectIndexThenThrowsNoSuchElementException() {
        list.get(6);
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
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(11));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(17));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(22));
        assertThat(iterator.hasNext(), is(false));
    }
}