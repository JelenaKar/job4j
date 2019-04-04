package ru.job4j.tree;

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
public class TreeTest {

    /**
     * Тестирование поиска существующего элемента.
     */
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    /**
     * Тестирование поиска несуществующего элемента.
     */
    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    /**
     * Тестирование вставки дублирующегося элемента.
     */
    @Test
    public void whenAddDuplicateElementThenReturnFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(2, 5);
        tree.add(5, 6);
        assertThat(
                tree.add(5, 6),
                is(false)
        );
    }

    /**
     * Тестирование вставки уникального элемента.
     */
    @Test
    public void whenAddUniqueChildThenReturnTrue() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(2, 5);
        tree.add(5, 6);
        assertThat(
                tree.add(5, 8),
                is(true)
        );
    }

    /**
     * Тестирование вставки элемента, в случае если родительского элемента не существует.
     */
    @Test
    public void whenParentNotFoundThenReturnFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(2, 3);
        assertThat(
                tree.add(5, 6),
                is(false)
        );
    }

    /**
     * Тестирование поведения итератора при конкурентном изменении коллекции.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenWasAddedThenIteratorThrowsConcurrentModificationException() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        Iterator iterator = tree.iterator();
        iterator.next();
        tree.add(2, 12);
        iterator.next();
    }

    /**
     * Тестирование поведения итератора при выходе за пределы контейнера.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenIteratorOnTheLastElementThenNextThrowsNoSuchException() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(2, 17);
        tree.add(2, 11);
        tree.add(2, 87);
        tree.add(87, 9);
        tree.add(4, 5);
        tree.add(5, 6);
        Iterator<Node<Integer>> iterator = tree.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
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

        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 4);
        tree.add(2, 17);
        tree.add(4, 5);
        tree.add(5, 6);
        Iterator<Node<Integer>> iterator = tree.iterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is(2));
        assertThat(iterator.next().getValue(), is(4));
        assertThat(iterator.next().getValue(), is(17));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is(5));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is(6));
        assertThat(iterator.hasNext(), is(false));
    }
}