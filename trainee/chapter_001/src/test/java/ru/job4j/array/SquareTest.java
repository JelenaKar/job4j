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
public class SquareTest {

    /**
     * Тест для массива из 3-х натуральных чисел.
     */
    @Test
    public void whenBound3ThenRes149() {
        Square square = new Square();
        int[] result = square.calculate(3);
        int[] expect = {1, 4, 9};
        assertThat(result, is(expect));
    }

    /**
     * Тест для массива из 5-ти натуральных чисел.
     */
    @Test
    public void whenBound5ThenRes1491625() {
        Square square = new Square();
        int[] result = square.calculate(5);
        int[] expect = {1, 4, 9, 16, 25};
        assertThat(result, is(expect));
    }

    /**
     * Тест для массива из 7-ми натуральных чисел.
     */
    @Test
    public void whenBound7ThenRes14916253649() {
        Square square = new Square();
        int[] result = square.calculate(7);
        int[] expect = {1, 4, 9, 16, 25, 36, 49};
        assertThat(result, is(expect));
    }

}