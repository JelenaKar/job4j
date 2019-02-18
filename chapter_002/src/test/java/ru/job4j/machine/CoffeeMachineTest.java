package ru.job4j.machine;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class CoffeeMachineTest {

    /**
     * Тест ситуации, когда достаточно денег и есть сдача.
     */
    @Test
    public void whenUserHaveEnoughMoney() {
        int[] expected = {10, 10, 10, 10, 5, 2, 1};
        CoffeeMachine machine = new CoffeeMachine();
        assertThat(machine.changes(100, 52), is(expected));
    }

    /**
     * Тест ситуации, когда денег под расчёт.
     */
    @Test
    public void whenUserHaveNotChange() {
        int[] expected = {0};
        CoffeeMachine machine = new CoffeeMachine();
        assertThat(machine.changes(50, 50), is(expected));
    }

    /**
     * Тест ситуации, когда денег недостаточно.
     */
    @Test (expected = NotEnoughMoney.class)
    public void whenUserHaveNotEnoughMoney() {
        CoffeeMachine machine = new CoffeeMachine();
        int[] change = machine.changes(50, 52);
    }
}
