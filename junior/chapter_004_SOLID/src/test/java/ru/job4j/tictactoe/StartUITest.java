package ru.job4j.tictactoe;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class StartUITest {
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
     * Тест вывода поля 3х3.
     */
    @Test
    public void whenGameHumanWithHumanAndYouMoveFirst() throws IOException {
        String[] answers = {"3", "3", "y", "n", "2", "2", "1", "3", "3", "3", "3", "2", "1", "1"};
        StubInput in = new StubInput(answers);
        new StartUI().start(in);

        StringBuilder expected = new StringBuilder()
                .append("Введите величину поля (от 3 до 10): ")
                .append("Введите длину победной комбинации (от 3 до 3): ")
                .append("Желаете ходить первым? (y/n): ")
                .append("Желаете играть с ботом? (y/n): ")
                .append(" | | ").append(System.lineSeparator())
                .append("-+-+-").append(System.lineSeparator())
                .append(" | | ").append(System.lineSeparator())
                .append("-+-+-").append(System.lineSeparator())
                .append(" | | ").append(System.lineSeparator()).append(System.lineSeparator())
                .append("Ходят крестики").append(System.lineSeparator())
                .append("Введите номер строки: ")
                .append("Введите номер столбца: ")
                .append(" | | ").append(System.lineSeparator())
                .append("-+-+-").append(System.lineSeparator())
                .append(" |X| ").append(System.lineSeparator())
                .append("-+-+-").append(System.lineSeparator())
                .append(" | | ").append(System.lineSeparator()).append(System.lineSeparator())
                .append("Ходят нолики").append(System.lineSeparator())
                .append("Введите номер строки: ")
                .append("Введите номер столбца: ")
                .append(" | |0").append(System.lineSeparator())
                .append("-+-+-").append(System.lineSeparator())
                .append(" |X| ").append(System.lineSeparator())
                .append("-+-+-").append(System.lineSeparator())
                .append(" | | ").append(System.lineSeparator()).append(System.lineSeparator())
                .append("Ходят крестики").append(System.lineSeparator())
                .append("Введите номер строки: ")
                .append("Введите номер столбца: ")
                .append(" | |0").append(System.lineSeparator())
                .append("-+-+-").append(System.lineSeparator())
                .append(" |X| ").append(System.lineSeparator())
                .append("-+-+-").append(System.lineSeparator())
                .append(" | |X").append(System.lineSeparator()).append(System.lineSeparator())
                .append("Ходят нолики").append(System.lineSeparator())
                .append("Введите номер строки: ")
                .append("Введите номер столбца: ")
                .append(" | |0").append(System.lineSeparator())
                .append("-+-+-").append(System.lineSeparator())
                .append(" |X| ").append(System.lineSeparator())
                .append("-+-+-").append(System.lineSeparator())
                .append(" |0|X").append(System.lineSeparator()).append(System.lineSeparator())
                .append("Ходят крестики").append(System.lineSeparator())
                .append("Введите номер строки: ")
                .append("Введите номер столбца: ")
                .append("X| |0").append(System.lineSeparator())
                .append("-+-+-").append(System.lineSeparator())
                .append(" |X| ").append(System.lineSeparator())
                .append("-+-+-").append(System.lineSeparator())
                .append(" |0|X").append(System.lineSeparator()).append(System.lineSeparator())
                .append("Победили крестики!").append(System.lineSeparator());
        assertThat(this.mem.toString(), is(expected.toString()));
    }
}