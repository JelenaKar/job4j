package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class MergeTest {

    /**
     * Тестирования случая, когда первый массив длиннее второго.
     */
    @Test
    public void whenFirstArrayIsLonger() {
        Merge mrg = new Merge();
        int[] arr1 = {1, 3, 8, 10, 15};
        int[] arr2 = {4, 5, 6, 9};
        int[] expected = {1, 3, 4, 5, 6, 8, 9, 10, 15};
        assertThat(mrg.merge(arr1, arr2), is(expected));
    }

    /**
     * Тестирования случая, когда второй массив длиннее первого.
     */
    @Test
    public void whenSecondArrayIsLonger() {
        Merge mrg = new Merge();
        int[] arr1 = {1, 3, 8, 10};
        int[] arr2 = {4, 5, 6, 10, 12};
        int[] expected = {1, 3, 4, 5, 6, 8, 10, 10, 12};
        assertThat(mrg.merge(arr1, arr2), is(expected));
    }
}
