package ru.job4j;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class CalculatorTest {

    /**
     * Тест для линейной функции.
     */
    @Test
    public void whenLinearFunctionThenLinearResults() {
        Calculator calculator = new Calculator();
        List<Double> result = calculator.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    /**
     * Тест для параболы.
     */
    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        Calculator calculator = new Calculator();
        List<Double> result = calculator.diapason(-3, 1, x -> x * x + 2 * x + 1);
        List<Double> expected = Arrays.asList(4D, 1D, 0D, 1D);
        assertThat(result, is(expected));
    }

    /**
     * Тест для логарифмической функции.
     */
    @Test
    public void whenLogarithmicFunctionThenLogarithmicResults() {
        Calculator calculator = new Calculator();
        List<Double> result = calculator.diapason(98, 101, Math::log10);
        List<Double> expected = Arrays.asList(1.991, 1.996, 2D);
        expected.forEach(x -> assertThat(result.iterator().next(), closeTo(x, 0.01)));
    }
}
