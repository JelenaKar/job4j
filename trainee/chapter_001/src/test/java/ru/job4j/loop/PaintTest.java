package ru.job4j.loop;

import org.junit.Test;
import java.util.StringJoiner;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Test.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PaintTest {
    /**
     * Тестирование результата для пирамиды высотой 2
     */
    @Test
    public void whenPyramid2() {
        Paint canvas = new Paint();
        String rst = canvas.pyramid(2);
        System.out.println(rst);
        assertThat(rst,
            is(
                new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add(" ^ ")
                                .add("^^^")
                                .toString()
                )
        );
    }

    /**
     * Тестирование результата для пирамиды высотой 4
     */
    @Test
    public void whenPyramid4() {
        Paint canvas = new Paint();
        String rst = canvas.pyramid(4);
        System.out.println(rst);
        assertThat(rst,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("   ^   ")
                                .add("  ^^^  ")
                                .add(" ^^^^^ ")
                                .add("^^^^^^^")
                                .toString()
                )
        );
    }

    /**
     * Тестирование результата для правой полупирамиды высотой 4
     */
    @Test
    public void whenPyramid4Right() {
        Paint canvas = new Paint();
        String rst = canvas.rightTrl(4);
        System.out.println(rst);
        assertThat(rst,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("^   ")
                                .add("^^  ")
                                .add("^^^ ")
                                .add("^^^^")
                                .toString()
                )
        );
    }

    /**
     * Тестирование результата для левой полупирамиды высотой 4
     */
    @Test
    public void whenPyramid4Left() {
        Paint canvas = new Paint();
        String rst = canvas.leftTrl(4);
        System.out.println(rst);
        assertThat(rst,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("   ^")
                                .add("  ^^")
                                .add(" ^^^")
                                .add("^^^^")
                                .toString()
                )
        );
    }
}
