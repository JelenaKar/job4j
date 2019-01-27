package ru.job4j.calculator;

/**
 * Простейший калькулятор.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 */
public class Calculator {

	/**
	 * Поле с результатом вычисления.
	 */
	private double result;

	/**
	 * Метод add.
	 * @param first Первое слагаемое.
	 * @param second Второе слагаемое.
	 */
	public void add(double first, double second) {
		this.result = first + second;
	}

	/**
	 * Метод substract.
	 * @param first Уменьшаемое.
	 * @param second Вычитаемое.
	 */
	public void subtract(double first, double second) {
		this.result = first - second;
	}

	/**
	 * Метод div.
	 * @param first Делимое.
	 * @param second Делитель.
	 */
	public void div(double first, double second) {
		this.result = first / second;
	}

	/**
	 * Метод multiple.
	 * @param first Первый множитель.
	 * @param second Второй множитель.
	 */
	public void multiple(double first, double second) {
		this.result = first * second;
	}

	/**
	 * Метод getResult.
	 *
	 * @return Результат вычислений.
	 */
	public double getResult() {
		return this.result;
	}

}