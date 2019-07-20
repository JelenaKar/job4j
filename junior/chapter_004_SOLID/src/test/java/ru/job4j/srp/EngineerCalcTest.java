package ru.job4j.srp;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EngineerCalcTest {

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
     * Тест проверки пункта меню "Рассчитать sin(x)".
     */
    @Test
    public void whenUserSelectSinThenReturnValueForRadians() {
        String input = "5" + LN + "1.57079632679" + LN + "n" + LN + "n" + LN + "9" + LN;
        EngineerCalc ic = new EngineerCalc(new ByteArrayInputStream(input.getBytes()));
        String expected = ic.getMenu()
                + "Введите 1-е число: "
                + "Результат равен: 1.0" + LN
                + "Сохранить результат для следующей операции? (y/n) "
                + "Повторить операцию? (y/n) "
                + ic.getMenu();
        this.templateTest(ic, expected);
    }

    /**
     * Тест проверки пункта меню "Рассчитать cos(x)".
     */
    @Test
    public void whenUserSelectCosThenReturnValueForRadians() {
        String input = "6" + LN + "0" + LN + "n" + LN + "n" + LN + "9" + LN;
        EngineerCalc ic = new EngineerCalc(new ByteArrayInputStream(input.getBytes()));
        String expected = ic.getMenu()
                + "Введите 1-е число: "
                + "Результат равен: 1.0" + LN
                + "Сохранить результат для следующей операции? (y/n) "
                + "Повторить операцию? (y/n) "
                + ic.getMenu();
        this.templateTest(ic, expected);
    }

    /**
     * Тест проверки пункта меню "Рассчитать tan(x)".
     */
    @Test
    public void whenUserSelectTanThenReturnValueForRadians() {
        String input = "7" + LN + Math.PI / 4 + LN + "n" + LN + "n" + LN + "9" + LN;
        EngineerCalc ic = new EngineerCalc(new ByteArrayInputStream(input.getBytes()));
        String expected = ic.getMenu()
                + "Введите 1-е число: "
                + "Результат равен: " + Math.tan(Math.PI / 4) + LN
                + "Сохранить результат для следующей операции? (y/n) "
                + "Повторить операцию? (y/n) "
                + ic.getMenu();
        this.templateTest(ic, expected);
    }

    /**
     * Тест проверки пункта меню "Рассчитать cot(x)".
     */
    @Test
    public void whenUserSelectCotThenReturnValueForRadians() {
        String input = "8" + LN + Math.PI / 4 + LN + "n" + LN + "n" + LN + "9" + LN;
        EngineerCalc ic = new EngineerCalc(new ByteArrayInputStream(input.getBytes()));
        String expected = ic.getMenu()
                + "Введите 1-е число: "
                + "Результат равен: " + 1 / Math.tan(Math.PI / 4) + LN
                + "Сохранить результат для следующей операции? (y/n) "
                + "Повторить операцию? (y/n) "
                + ic.getMenu();
        this.templateTest(ic, expected);
    }
}