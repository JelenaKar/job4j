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
public class CheckTest {

    /**
     * Тест для однородного массива с нечётным количеством элементов.
     */
    @Test
    public void whenDataMonoByTrueThenTrueOdd() {
        Check array = new Check();
        boolean[] input = new boolean[] {true, true, true};
        boolean result = array.mono(input);
        assertThat(result, is(true));
    }

    /**
     * Тест для неоднородного массива с нечётным количеством элементов.
     */
    @Test
    public void whenDataNotMonoByTrueThenFalseOdd() {
        Check array = new Check();
        boolean[] input = new boolean[] {true, false, true};
        boolean result = array.mono(input);
        assertThat(result, is(false));
    }

    /**
     * Тест для однородного массива с чётным количеством элементов.
     */
    @Test
    public void whenDataMonoByFalseThenTrueEven() {
        Check array = new Check();
        boolean[] input = new boolean[] {false, false, false, false};
        boolean result = array.mono(input);
        assertThat(result, is(true));
    }

    /**
     * Тест для неоднородного массива с чётным количеством элементов.
     */
    @Test
    public void whenDataNotMonoByTrueThenFalseEven() {
        Check array = new Check();
        boolean[] input = new boolean[] {true, true, false, true};
        boolean result = array.mono(input);
        assertThat(result, is(false));
    }
}