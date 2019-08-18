package ru.job4j.tictactoe;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
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
public class SquareTest {

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
    public void whenShowSquareWithSize3() {
        Square square = new Square(3);
        square.show();
        StringBuilder expected = new StringBuilder()
                .append(" | | ").append(System.lineSeparator())
                .append("-+-+-").append(System.lineSeparator())
                .append(" | | ").append(System.lineSeparator())
                .append("-+-+-").append(System.lineSeparator())
                .append(" | | ").append(System.lineSeparator()).append(System.lineSeparator());
        assertThat(this.mem.toString(), is(expected.toString()));
    }

    /**
     * Тест вывода поля 5х5.
     */
    @Test
    public void whenShowSquareWithSize5() {
        Square square = new Square(5);
        square.show();
        StringBuilder expected = new StringBuilder()
                .append(" | | | | ").append(System.lineSeparator())
                .append("-+-+-+-+-").append(System.lineSeparator())
                .append(" | | | | ").append(System.lineSeparator())
                .append("-+-+-+-+-").append(System.lineSeparator())
                .append(" | | | | ").append(System.lineSeparator())
                .append("-+-+-+-+-").append(System.lineSeparator())
                .append(" | | | | ").append(System.lineSeparator())
                .append("-+-+-+-+-").append(System.lineSeparator())
                .append(" | | | | ").append(System.lineSeparator()).append(System.lineSeparator());
        assertThat(this.mem.toString(), is(expected.toString()));
    }

    /**
     * Тест вставки крестика и нолика и вывод поля на экран.
     */
    @Test
    public void whenInsertCrossAndZeroAndShowSquareWithSize3() {
        Square square = new Square(3);
        square.insert(new int[]{2, 2, Square.CROSS});
        square.insert(new int[]{3, 1, Square.ZERO});
        square.show();
        StringBuilder expected = new StringBuilder()
                .append(" | | ").append(System.lineSeparator())
                .append("-+-+-").append(System.lineSeparator())
                .append(" |X| ").append(System.lineSeparator())
                .append("-+-+-").append(System.lineSeparator())
                .append("0| | ").append(System.lineSeparator()).append(System.lineSeparator());
        assertThat(this.mem.toString(), is(expected.toString()));
    }

    /**
     * Проверка на наличие свободных ячеек для пустого поля.
     */
    @Test
    public void whenSquareHasFreeCellsThenReturnTrue() {
        Square square = new Square(3);
        assertThat(square.hasFree(), is(true));
    }

    /**
     * Проверка на наличие свободных ячеек для заполненного поля.
     */
    @Test
    public void whenSquareHasNotFreeCellsThenReturnFalse() {
        Square square = new Square(3);
        square.insert(new int[]{1, 1, Square.ZERO});
        square.insert(new int[]{1, 2, Square.CROSS});
        square.insert(new int[]{1, 3, Square.CROSS});
        square.insert(new int[]{2, 1, Square.CROSS});
        square.insert(new int[]{2, 2, Square.CROSS});
        square.insert(new int[]{2, 3, Square.ZERO});
        square.insert(new int[]{3, 1, Square.ZERO});
        square.insert(new int[]{3, 2, Square.ZERO});
        square.insert(new int[]{3, 3, Square.CROSS});
        assertThat(square.hasFree(), is(false));
    }

}