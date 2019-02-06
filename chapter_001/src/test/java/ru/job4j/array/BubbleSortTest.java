package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BubbleSortTest {

    /**
     * Сортировка целочисленного массива из 10 элементов.
     */
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        int[] array = {1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
        int[] expect = {0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
        BubbleSort bubbleSort = new BubbleSort();
        assertThat(bubbleSort.sort(array), is(expect));
    }
}