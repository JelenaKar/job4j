package ru.job4j.threadsafe;

import org.junit.Before;
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
public class UserStorageTest {
    private UserStorage bank = new UserStorage();
    @Before
    public void prepareData() {
        bank.add(new User(1, 2000));
        bank.add(new User(2, 1000));
    }

    /**
     * Тестирование ситуации, когда сумма перевода превышает имеющиеся средства.
     */
    @Test
    public void whenSentOverflowAmountThenNoAction() throws InterruptedException {
        Thread thread = new Thread(
                () -> bank.transfer(2, 1, 2000)
        );
        thread.start();
        thread.join();
        assertThat(bank.getStorage().get(1).getAmount(), is(2000.0));
        assertThat(bank.getStorage().get(2).getAmount(), is(1000.0));
    }

    /**
     * Тестирование двух транзакций.
     */
    @Test
    public void whenTransferThenAmountsChangeAndTotalIsTheSame() throws InterruptedException {
        Thread thread1 = new Thread(
                () -> bank.transfer(2, 1, 500)
        );
        Thread thread2 = new Thread(
                () -> bank.transfer(1, 2, 200)
        );
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        assertThat(bank.getStorage().get(1).getAmount(), is(2300.0));
        assertThat(bank.getStorage().get(2).getAmount(), is(700.0));
        assertThat(bank.getTotal(), is(3000.0));
    }

}