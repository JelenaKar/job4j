package ru.job4j.stream;

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
public class ArrayFilterTest {

    /**
     * Тест фильтрации массива.
     */
    @Test
    public void whenFilterArrayWithStreamThenReceiveSumOfSquares() {
        int[] array = {1, 2, 3, 4, 6, 13, 15, 20};
        assertThat(ArrayFilter.streamArrayFilter(array), is(456));
    }
}