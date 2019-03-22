package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class LinkedCycledListTest {

    private LinkedCycledList<Integer> list = new LinkedCycledList<>();

    /**
     * Тестирование ситуации, когда отсутствует зацикленность.
     */
    @Test
    public void whenNoCycledThenFalse() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(11);
        Node<Integer> third = new Node<>(17);
        Node<Integer> fourth = new Node<>(22);
        first.next = second;
        second.next = third;
        third.next = fourth;
        assertThat(list.hasCycle(first), is(false));
    }

    /**
     * Тестирование ситуации, когда присутствует зацикленность.
     */
    @Test
    public void whenCycledThenTrue() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(11);
        Node<Integer> third = new Node<>(17);
        Node<Integer> fourth = new Node<>(22);
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = third;
        assertThat(list.hasCycle(first), is(true));
    }

    /**
     * Тестирование ситуации, список пустой.
     */
    @Test
    public void whenLinkedListIsEmptyThenReturnFalse() {
        Node<Integer> first = null;
        Node<Integer> second = null;
        Node<Integer> third = null;
        Node<Integer> fourth = null;
        assertThat(list.hasCycle(first), is(false));
    }

}