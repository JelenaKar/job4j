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
public class MatrixCheckTest {

    /**
     * Тест для однородной матрицы с нечётной размерностью.
     */
    @Test
    public void whenDataMonoByTrueThenTrueOdd() {
        MatrixCheck matrix = new MatrixCheck();
        boolean[][] input = new boolean[][] {
                {true, true, true},
                {false, true, true},
                {true, false, true}
        };
        boolean result = matrix.mono(input);
        assertThat(result, is(true));
    }

    /**
     * Тест для неоднородной матрицы с нечётной размерностью.
     */
    @Test
    public void whenDataNotMonoByTrueThenFalseOdd() {
        MatrixCheck matrix = new MatrixCheck();
        boolean[][] input = new boolean[][] {
                {true, true, false},
                {false, false, true},
                {true, false, true}
        };
        boolean result = matrix.mono(input);
        assertThat(result, is(false));
    }

    /**
     * Тест для однородной матрицы с чётной размерностью.
     */
    @Test
    public void whenDataMonoByTrueThenTrueEven() {
        MatrixCheck matrix = new MatrixCheck();
        boolean[][] input = new boolean[][] {
                {true, false},
                {false, true}};
        boolean result = matrix.mono(input);
        assertThat(result, is(true));
    }

    /**
     * Тест для неоднородной матрицы с чётной размерностью.
     */
    @Test
    public void whenDataNotMonoByTrueThenFalseEven() {
        MatrixCheck matrix = new MatrixCheck();
        boolean[][] input = new boolean[][] {
                {true, true, false, false},
                {false, false, false, true},
                {true, false, true, true},
                {false, false, true, true}
        };
        boolean result = matrix.mono(input);
        assertThat(result, is(false));
    }
}