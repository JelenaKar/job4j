package ru.job4j.tictactoe;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class LogicTest {

    /**
     * Тест победы крестиков по главной диагональной комбинации.
     */
    @Test
    public void whenWinsCrossWithCrossCombination() throws IOException {
        int size = 3;
        int combination = 3;
        String[] answers = {"2", "2", "1", "3", "3", "3", "3", "2", "1", "1"};
        StubInput in = new StubInput(answers);
        Map<Integer, User> players = new TreeMap<>(Map.of(1, new Human(1, size, in), -1, new Human(-1, size, in)));
        Logic play = new Logic(players, size, combination);
        play.play();
        assertThat(play.winner().getSign(), is(Square.CROSS));
    }
    /**
     * Тест победы ноликов по побочной диагональной комбинации.
     */
    @Test
    public void whenWinsZeroWithReverseCrossCombination() throws IOException {
        int size = 3;
        int combination = 3;
        String[] answers = {"3", "3", "2", "2", "1", "1", "3", "1", "2", "3", "1", "3"};
        StubInput in = new StubInput(answers);
        Map<Integer, User> players = new TreeMap<>(Map.of(1, new Human(1, size, in), -1, new Human(-1, size, in)));
        Logic play = new Logic(players, size, combination);
        play.play();
        assertThat(play.winner().getSign(), is(Square.ZERO));
    }

    /**
     * Тест победы крестиков по горизонтальной комбинации.
     */
    @Test
    public void whenWinsCrossWithHorisontalCombination() throws IOException {
        int size = 3;
        int combination = 3;
        String[] answers = {"2", "3", "1", "1", "2", "1", "3", "1", "2", "2"};
        StubInput in = new StubInput(answers);
        Map<Integer, User> players = new TreeMap<>(Map.of(1, new Human(1, size, in), -1, new Human(-1, size, in)));
        Logic play = new Logic(players, size, combination);
        play.play();
        assertThat(play.winner().getSign(), is(Square.CROSS));
    }

    /**
     * Тест победы ноликов по вертикальной комбинации.
     */
    @Test
    public void whenWinsZeroWithVerticalCombination() throws IOException {
        int size = 3;
        int combination = 3;
        String[] answers = {"2", "2", "3", "3", "1", "1", "1", "3", "3", "1", "2", "3"};
        StubInput in = new StubInput(answers);
        Map<Integer, User> players = new TreeMap<>(Map.of(1, new Human(1, size, in), -1, new Human(-1, size, in)));
        Logic play = new Logic(players, size, combination);
        play.play();
        assertThat(play.winner().getSign(), is(Square.ZERO));
    }

    /**
     * Тест ничьей.
     */
    @Test
    public void whenStandOffThenReturnNull() throws IOException {
        int size = 3;
        int combination = 3;
        String[] answers = {"2", "2", "1", "1", "1", "3", "3", "1", "2", "1", "2", "3", "3", "2", "1", "2", "3", "3"};
        StubInput in = new StubInput(answers);
        Map<Integer, User> players = new TreeMap<>(Map.of(1, new Human(1, size, in), -1, new Human(-1, size, in)));
        Logic play = new Logic(players, size, combination);
        play.play();
        User expected = null;
        assertThat(play.winner(), is(expected));
    }
}