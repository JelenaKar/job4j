package ru.job4j.loop;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class FindLoopTest {

    /**
     * Тест, когда искомое число присутствует.
     */
    @Test
    public void whenArrayHas5Then2() {
        FindLoop find = new FindLoop();
        int[] arr = {100, 0, 5, 17, 22};
        int res = find.indexOf(arr, 5);
        assertThat(res, is(2));
    }

    /**
     * Тест, когда искомое число отсутствует.
     */
    @Test
    public void whenArrayHasnt5ThenMin1() {
        FindLoop find = new FindLoop();
        int[] arr = {100, 0, 10};
        int res = find.indexOf(arr, 5);
        assertThat(res, is(-1));
    }
}