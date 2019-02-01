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
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][] {
                {true, true, true},
                {false, true, true},
                {true, false, true}
        };
        boolean result = check.mono(input);
        assertThat(result, is(true));
    }

    /**
     * Тест для неоднородной матрицы с нечётной размерностью.
     */
    @Test
    public void whenDataNotMonoByTrueThenFalseOdd() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][] {
                {true, true, false},
                {false, false, true},
                {true, false, true}
        };
        boolean result = check.mono(input);
        assertThat(result, is(false));
    }

    /**
     * Тест для однородной матрицы с чётной размерностью.
     */
    @Test
    public void whenDataMonoByTrueThenTrueEven() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][] {
                {true, false},
                {false, true}};
        boolean result = check.mono(input);
        assertThat(result, is(true));
    }

    /**
     * Тест для неоднородной матрицы с чётной размерностью.
     */
    @Test
    public void whenDataNotMonoByTrueThenFalseEven() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][] {
                {true, true, false, false},
                {false, false, false, true},
                {true, false, true, true},
                {false, false, true, true}
        };
        boolean result = check.mono(input);
        assertThat(result, is(false));
    }
}