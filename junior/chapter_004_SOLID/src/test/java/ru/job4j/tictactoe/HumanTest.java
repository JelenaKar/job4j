package ru.job4j.tictactoe;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class HumanTest {

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
     * Тест ввода нечисловых значений.
     */
    @Test
    public void whenEnterNonnumericThenWarningAndAskToEnterAgain() {
        String[] answers = {"j", "3", "y", "2"};
        StubInput in = new StubInput(answers);
        Human human = new Human(Square.ZERO, 5, in);
        int[] move = human.move();
        String expected = "Введите номер строки: "
                + "Значение не является корректным целым числом!" + System.lineSeparator()
                + "Введите номер строки: "
                + "Введите номер столбца: "
                + "Значение не является корректным целым числом!" + System.lineSeparator()
                + "Введите номер столбца: ";
        assertThat(this.mem.toString(), is(expected));
        assertTrue(move[0] == 3 && move[1] == 2);
        assertThat(move[2], is(Square.ZERO));
    }

    /**
     * Тест ввода целых чисел, выходящих из диапазона допустимых значений.
     */
    @Test
    public void whenEnterIntegerOutOfRangeThenWarningAndAskToEnterAgain() {
        String[] answers = {"0", "2", "6", "5"};
        StubInput in = new StubInput(answers);
        Human human = new Human(Square.ZERO, 5, in);
        int[] move = human.move();
        String expected = "Введите номер строки: "
                + "Значение должно быть в диапазоне от 1 до 5" + System.lineSeparator()
                + "Введите номер строки: "
                + "Введите номер столбца: "
                + "Значение должно быть в диапазоне от 1 до 5" + System.lineSeparator()
                + "Введите номер столбца: ";
        assertThat(this.mem.toString(), is(expected));
        assertTrue(move[0] == 2 && move[1] == 5);
        assertThat(move[2], is(Square.ZERO));
    }
}