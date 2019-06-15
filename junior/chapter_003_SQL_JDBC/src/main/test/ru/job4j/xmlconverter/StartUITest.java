package ru.job4j.xmlconverter;

import org.junit.Test;

import java.io.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class StartUITest {

    public final static String LN = System.lineSeparator();

    /**
     * Проверка рассчёта с имитацией двух неверных вводов.
     */
    @Test
    public void whenImitateUsersBehavior() throws IOException {
        String words = String.format("f%s0%s50", LN, LN);
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(words.getBytes())));
             PrintStream out = new PrintStream(stdout)) {
            new StartUI(in, out).start();
            String expected = new StringBuilder().append("Введите целое положительное число: ")
                .append("Ошибка ввода! Введите целое положительное число: ")
                .append("Ошибка ввода! Введите целое положительное число: ")
                .append("Сумма всех элементов: 1275")
                .toString();
             assertThat(stdout.toString(), is(expected));
        }
    }

}