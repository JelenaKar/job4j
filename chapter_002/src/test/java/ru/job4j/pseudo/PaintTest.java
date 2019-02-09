package ru.job4j.pseudo;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PaintTest {

    /**
     * Тестирование стратегии - рисовать квадрат.
     */
    @Test
    public void whenDrawSquare() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square());
        String ln = System.lineSeparator();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("******").append(ln)
                                .append("*    *").append(ln)
                                .append("*    *").append(ln)
                                .append("******").append(ln)
                                .toString()
                )
        );
        System.setOut(stdout);
    }

    /**
     * Тестирование стратегии - рисовать треугольник.
     */
    @Test
    public void whenDrawTriangle() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Triangle());
        String ln = System.lineSeparator();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("   *").append(ln)
                                .append("  * *").append(ln)
                                .append(" *   *").append(ln)
                                .append("*******").append(ln)
                                .toString()
                )
        );
        System.setOut(stdout);
    }
}