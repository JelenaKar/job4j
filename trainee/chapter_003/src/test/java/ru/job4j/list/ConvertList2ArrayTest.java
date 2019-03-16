package ru.job4j.list;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConvertList2ArrayTest {

    /**
     * Тест ситуации для списка из 7 элементов.
     */
    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                List.of(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    /**
     * Тест ситуации для двумерного списка с разным количеством элементов в каждом.
     */
    @Test
    public void when2ArraysWithSize25ThenListWith7Elements() {
        ConvertList2Array converter = new ConvertList2Array();

        List<int[]> list = List.of(new int[]{1, 2}, new int[]{3, 4, 5, 6, 7});

        List<Integer> result = converter.convert(list);

        List<Integer> expect = List.of(1, 2, 3, 4, 5, 6, 7);
        assertThat(result, is(expect));
    }
}
