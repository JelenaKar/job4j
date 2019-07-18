package ru.job4j.srp;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class InteractCalcTest {

    private static final String LN = System.lineSeparator();

    public void templateTest(InteractCalc ic, String expected) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ic.changeOutput(new PrintStream(out));
            ic.start();
            assertThat(out.toString(), is(expected));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Тест ввода текста вместо номера меню.
     */
    @Test
    public void whenUserInputsStringThenShowInfoMsg() {
        String input = "jh" + LN + "5" + LN;
        InteractCalc ic = new InteractCalc(new ByteArrayInputStream(input.getBytes()));
        String expected = ic.getMenu() + "Операция должна быть целым числом в интервале 1-5!" + LN
                + ic.getMenu();
        this.templateTest(ic, expected);
    }

    /**
     * Тест ввода целого числа не из интервала.
     */
    @Test
    public void whenUserInputNotInIntervalThenShowInfoMsg() {
        String input = "0" + LN + "5" + LN;
        InteractCalc ic = new InteractCalc(new ByteArrayInputStream(input.getBytes()));
        String expected = ic.getMenu() + "Операция должна быть целым числом в интервале 1-5!" + LN
                + ic.getMenu();
        this.templateTest(ic, expected);
    }

    /**
     * Тест проверки пункта меню "1. Сложение".
     */
    @Test
    public void whenUserInputOneThenSumTwoDoubles() {
        String input = "1" + LN + "5.2" + LN + "-3.2" + LN + "n" + LN + "n" + LN + "5" + LN;
        InteractCalc ic = new InteractCalc(new ByteArrayInputStream(input.getBytes()));
        String expected = ic.getMenu()
                + "Введите 1-е число: " + "Введите 2-е число: "
                + "Результат равен: 2.0" + LN
                + "Сохранить результат для следующей операции? (y/n) "
                + "Повторить операцию? (y/n) "
                + ic.getMenu();
        this.templateTest(ic, expected);
    }

    /**
     * Тест проверки пункта меню "2. Вычитание".
     */
    @Test
    public void whenUserInputTwoThenSubstractTwoDoubles() {
        String input = "2" + LN + "5" + LN + "3" + LN + "n" + LN + "n" + LN + "5" + LN;
        InteractCalc ic = new InteractCalc(new ByteArrayInputStream(input.getBytes()));
        String expected = ic.getMenu()
                + "Введите 1-е число: " + "Введите 2-е число: "
                + "Результат равен: 2.0" + LN
                + "Сохранить результат для следующей операции? (y/n) "
                + "Повторить операцию? (y/n) "
                + ic.getMenu();
        this.templateTest(ic, expected);
    }

    /**
     * Тест проверки пункта меню "3. Умножение".
     */
    @Test
    public void whenUserInputThreeThenMultiplyTwoDoubles() {
        String input = "3" + LN + "5.2" + LN + "3.3" + LN + "n" + LN + "n" + LN + "5" + LN;
        InteractCalc ic = new InteractCalc(new ByteArrayInputStream(input.getBytes()));
        String expected = ic.getMenu()
                + "Введите 1-е число: " + "Введите 2-е число: "
                + "Результат равен: 17.16" + LN
                + "Сохранить результат для следующей операции? (y/n) "
                + "Повторить операцию? (y/n) "
                + ic.getMenu();
        this.templateTest(ic, expected);
    }

    /**
     * Тест проверки пункта меню "4. Деление".
     */
    @Test
    public void whenUserInputFourThenDivideTwoDoubles() {
        String input = "4" + LN + "15" + LN + "-3" + LN + "n" + LN + "n" + LN + "5" + LN;
        InteractCalc ic = new InteractCalc(new ByteArrayInputStream(input.getBytes()));
        String expected = ic.getMenu()
                + "Введите 1-е число: " + "Введите 2-е число: "
                + "Результат равен: -5.0" + LN
                + "Сохранить результат для следующей операции? (y/n) "
                + "Повторить операцию? (y/n) "
                + ic.getMenu();
        this.templateTest(ic, expected);
    }

    /**
     * Тест использования результата расчёта в следующей операции.
     */
    @Test
    public void whenUserSaveResultThenUsesItAsFirstDouble() {
        String input = "4" + LN + "15" + LN + "-3" + LN + "y" + LN + "n" + LN
                + "1" + LN + "6" + LN + "n" + LN + "n" + LN + "5" + LN;
        InteractCalc ic = new InteractCalc(new ByteArrayInputStream(input.getBytes()));
        String expected = ic.getMenu()
                + "Введите 1-е число: " + "Введите 2-е число: "
                + "Результат равен: -5.0" + LN
                + "Сохранить результат для следующей операции? (y/n) "
                + "Повторить операцию? (y/n) "
                + ic.getMenu()
                + "Введите 2-е число: "
                + "Результат равен: 1.0" + LN
                + "Сохранить результат для следующей операции? (y/n) "
                + "Повторить операцию? (y/n) "
                + ic.getMenu();
        this.templateTest(ic, expected);
    }

    /**
     * Тест повторения операции.
     */
    @Test
    public void whenUserRepeatsOperationThenInputOnlyDoubles() {
        String input = "3" + LN + "2" + LN + "-3" + LN + "n" + LN + "y" + LN
                + "2" + LN + "6" + LN + "n" + LN + "n" + LN + "5" + LN;
        InteractCalc ic = new InteractCalc(new ByteArrayInputStream(input.getBytes()));
        String expected = ic.getMenu()
                + "Введите 1-е число: " + "Введите 2-е число: "
                + "Результат равен: -6.0" + LN
                + "Сохранить результат для следующей операции? (y/n) "
                + "Повторить операцию? (y/n) "
                + "Введите 1-е число: " + "Введите 2-е число: "
                + "Результат равен: 12.0" + LN
                + "Сохранить результат для следующей операции? (y/n) "
                + "Повторить операцию? (y/n) "
                + ic.getMenu();
        this.templateTest(ic, expected);
    }
}