package ru.job4j.pseudo;

import org.junit.After;
import org.junit.Before;
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

    private PrintStream stdout = System.out;
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    /**
     * Тестирование стратегии - рисовать квадрат.
     */
    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square());
        String ln = System.lineSeparator();
        assertThat(
                out.toString(),
                is(
                        new StringBuilder()
                                .append("******").append(ln)
                                .append("*    *").append(ln)
                                .append("*    *").append(ln)
                                .append("******").append(ln)
                                .toString()
                )
        );
    }

    /**
     * Тестирование стратегии - рисовать треугольник.
     */
    @Test
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle());
        String ln = System.lineSeparator();
        assertThat(
                out.toString(),
                is(
                        new StringBuilder()
                                .append("   *").append(ln)
                                .append("  * *").append(ln)
                                .append(" *   *").append(ln)
                                .append("*******").append(ln)
                                .toString()
                )
        );
    }
}