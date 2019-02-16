package ru.job4j;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.chess.FigureNotFoundException;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.Logic;
import ru.job4j.chess.OccupiedWayException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.base.Bishop;
import ru.job4j.chess.firuges.white.BishopWhite;
import ru.job4j.chess.firuges.white.PawnWhite;
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
public class ChessTest {
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
     * Тест перемещения пустой ячейки.
     */
    @Test (expected = FigureNotFoundException.class)
    public void whenMoveEmptyCellThenFigureNotFoundException() {
        Logic logic = new Logic();
        Bishop whiteBishop = new BishopWhite(Cell.C1);
        logic.add(whiteBishop);
        logic.move(Cell.A3, Cell.D6);
    }

    /**
     * Тест перемещения белого слона диагонально по свободному пути.
     */
    @Test
    public void whenMoveBishopDiagonallyThenTrue() {
        Logic logic = new Logic();
        Bishop whiteBishop = new BishopWhite(Cell.A3);
        logic.add(whiteBishop);
        boolean rst = logic.move(Cell.A3, Cell.D6);
        assertThat(rst, is(true));
    }

    /**
     * Тест ошибочного перемещения белого слона.
     */
    @Test (expected = ImpossibleMoveException.class)
    public void whenMoveBishopWrongThenImpossibleMoveException() {
        Logic logic = new Logic();
        Bishop whiteBishop = new BishopWhite(Cell.A3);
        logic.add(whiteBishop);
        logic.move(Cell.A3, Cell.D8);
    }

    /**
     * Тест перемещения белого слона диагонально, когда путь преграждает пешка.
     */
    @Test (expected = OccupiedWayException.class)
    public void whenMoveBishopAlongOccupiedWayThenOccupiedWayException() {
        Logic logic = new Logic();
        Bishop whiteBishop = new BishopWhite(Cell.C1);
        PawnWhite whitePawn = new PawnWhite(Cell.B2);
        logic.add(whiteBishop);
        logic.add(whitePawn);
        logic.move(Cell.C1, Cell.A3);
    }
}
