package ru.job4j.tracker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ValidateInputTest {
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    /**
     * Тест ввода нечислового значения.
     */
    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"invalid", "1"})
        );
        List<Integer> range = new ArrayList<>();
        range.add(1);
        input.ask("Enter", range);
        assertThat(
                this.mem.toString(),
                is(
                        String.format("Введите валидное целое число для выбора пункта меню!%n")
                )
        );
    }

    /**
     * Тест ввода числа вне диапазона.
     */
    @Test
    public void whenOutOfRangeInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"20", "1"})
        );
        List<Integer> range = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            range.add(i);
        }
        input.ask("Enter", range);
        assertThat(
                this.mem.toString(),
                is(
                        String.format("Введите число из диапазона пунктов меню!%n")
                )
        );
    }

    /**
     * Тест ввода корректного числа.
     */
    @Test
    public void whenValidInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"6"})
        );
        List<Integer> range = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            range.add(i);
        }
        input.ask("Enter", range);
        assertThat(
                this.mem.toString(),
                is(
                        ""
                )
        );
    }
}
