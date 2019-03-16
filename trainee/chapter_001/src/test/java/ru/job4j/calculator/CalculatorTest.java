package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Calculator Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class CalculatorTest {
	/**
	 * Test add method.
	 */
	@Test
	public void whenAddOnePlusOneThenTwo()  {
		Calculator calc = new Calculator();
		calc.add(1D, 1D);
		double result = calc.getResult();
		double expected = 2D;
		assertThat(result, is(expected));
	}

	/**
	 * Test subtract method.
	 */
	@Test
	public void whenSubtractFiveMinusTwoThenThree()  {
		Calculator calc = new Calculator();
		calc.subtract(5D, 2D);
		double result = calc.getResult();
		double expected = 3D;
		assertThat(result, is(expected));
	}

	/**
	 * Test div method.
	 */
	@Test
	public void whenDivTwoOnTwoThenOne()  {
		Calculator calc = new Calculator();
		calc.div(2D, 2D);
		double result = calc.getResult();
		double expected = 1D;
		assertThat(result, is(expected));
	}

	/**
	 * Test multiplicate method.
	 */
	@Test
	public void whenMultipleTwoOnTwoThenFour()  {
		Calculator calc = new Calculator();
		calc.multiple(2D, 2D);
		double result = calc.getResult();
		double expected = 4D;
		assertThat(result, is(expected));
	}
}