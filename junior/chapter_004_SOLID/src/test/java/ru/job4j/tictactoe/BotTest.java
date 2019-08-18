package ru.job4j.tictactoe;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BotTest {

    /**
     * Тестирует ход бота.
     */
    @Test
    public void whenBotMoveThenReturnArrayWithCoordinatesWithinSquareSize() {
        Bot bot = new Bot(Square.CROSS, 5);
        int[] move = bot.move();
        assertTrue(move[0] >= 1 && move[0] <= 5);
        assertTrue(move[1] >= 1 && move[1] <= 5);
        assertThat(move[2], is(Square.CROSS));
    }

}