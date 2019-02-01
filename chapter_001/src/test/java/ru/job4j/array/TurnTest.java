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
public class TurnTest {

    /**
     * Тест для массива с нечётным количеством элементов.
     */
    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int[] expect = {7, 6, 5, 4, 3, 2, 1};

        Turn trn = new Turn();

        assertThat(trn.back(arr), is(expect));
    }

    /**
     * Тест для массива с чётным количеством элементов.
     */
    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        int[] arr = {1, 2, 3, 4};
        int[] expect = {4, 3, 2, 1};

        Turn trn = new Turn();

        assertThat(trn.back(arr), is(expect));
    }
}
