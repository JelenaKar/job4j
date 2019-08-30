package ru.job4j.cash;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class CashTest {

    /**
     * Тест кеширования файла при его отсутствии.
     */
    @Test
    public void whenFileIsNotCachedThenAddItIntoCash() {
        Cash cash = new Cash();
        String expected = null;
        assertThat(cash.getStorage().get(new SoftRefKey<>("./textfiles/test.txt")), is(expected));
        expected = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                + System.lineSeparator() + "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
        assertThat(cash.receive("./textfiles/test.txt"), is(expected));
        assertThat(cash.getStorage().get(new SoftRefKey<>("./textfiles/test.txt")), is(expected));
    }
}