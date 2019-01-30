package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class FactorialTest {

    /**
     * Проверка, что 5! равен 120.
     */
    @Test
    public void whenCalculateFactorialForFiveThenOneHundreedTwenty() {
        Factorial cnt = new Factorial();
        int result = cnt.calc(5);
        assertThat(result, is(120));
    }

    /**
     * Проверка, что 0! равен 1.
     */
    @Test
    public void whenCalculateFactorialForZeroThenOne() {
        Factorial cnt = new Factorial();
        int result = cnt.calc(0);
        assertThat(result, is(1));
    }
}

