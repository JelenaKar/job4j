package ru.job4j.bank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class OperationsTest {

    /**
     * Тестирование добавления и удаления пользователя.
     */
    @Test
    public void whenAddAndDeleteUserIntoBank() {
        Bank bankOperations = new Bank();
        User user = new User("Иванов Иван", "6001 123141");
        bankOperations.addUser(user);
        assertThat(bankOperations.getAllAccounts().containsKey(user), is(true));
        bankOperations.deleteUser(user);
        assertThat(bankOperations.getAllAccounts().containsKey(user), is(false));
    }

    /**
     * Тестирование добавления и удаления счёта пользователя.
     */
    @Test
    public void whenAddAndDeleteAccountToUser() {
        Bank bank = new Bank();
        User vanya = new User("Иванов Иван", "6001 123141");
        bank.addUser(vanya);
        Account vanyaAccount = new Account(5000, "123456789");
        bank.addAccountToUser(vanya.getPassport(), vanyaAccount);
        assertThat(bank.getAllAccounts().get(vanya).contains(vanyaAccount), is(true));
        bank.deleteAccountFromUser(vanya.getPassport(), vanyaAccount);
        assertThat(bank.getAllAccounts().get(vanya).contains(vanyaAccount), is(false));
    }

    /**
     * Тестирование выгрузки всех счетов пользователя.
     */
    @Test
    public void whenListAllUserAccounts() {
        Bank bank = new Bank();
        User vanya = new User("Иванов Иван", "6001 123141");
        bank.addUser(vanya);

        Account vanyaRub = new Account(5000, "123456789");
        Account vanyaEur = new Account(1000, "789156738");
        List<Account> expected = new ArrayList<>(Arrays.asList(vanyaRub, vanyaEur));

        bank.addAccountToUser(vanya.getPassport(), vanyaRub);
        bank.addAccountToUser(vanya.getPassport(), vanyaEur);

        assertThat(bank.getUserAccounts(vanya.getPassport()), is(expected));
    }

    /**
     * Тестирование перевода со счёта на счёт.
     */
    @Test
    public void whenSuccessfulTransferMoneyFromOneUserToOther() {
        Bank bank = new Bank();
        User vanya = new User("Иванов Иван", "6001 123141");
        User vasya = new User("Петров Василий", "5090 654789");
        bank.addUser(vanya);
        bank.addUser(vasya);

        Account vanyaRub = new Account(5000, "123456789");
        Account vasyaRub = new Account(1000, "789156738");
        bank.addAccountToUser(vanya.getPassport(), vanyaRub);
        bank.addAccountToUser(vasya.getPassport(), vasyaRub);

        bank.transferMoney(vanya.getPassport(), vanyaRub.getRequisites(),
                vasya.getPassport(), vasyaRub.getRequisites(), 2000);

        assertThat(bank.getAllAccounts().get(vanya).get(0).getValue(), is(3000.0));
        assertThat(bank.getAllAccounts().get(vasya).get(0).getValue(), is(3000.0));
    }

    /**
     * Тестирование перевода со счёта на счёт.
     */
    @Test
    public void whenTransferMoneyFromWrongAccountThenFail() {
        Bank bank = new Bank();
        User vanya = new User("Иванов Иван", "6001 123141");
        User vasya = new User("Петров Василий", "5090 654789");
        bank.addUser(vanya);
        bank.addUser(vasya);

        Account vanyaRub = new Account(5000, "123456789");
        Account vasyaRub = new Account(1000, "789156738");
        bank.addAccountToUser(vanya.getPassport(), vanyaRub);
        bank.addAccountToUser(vasya.getPassport(), vasyaRub);

        bank.transferMoney(vanya.getPassport(), new Account(2000, "7456123048").getRequisites(),
                vasya.getPassport(), vasyaRub.getRequisites(), 2000);

        assertThat(bank.getAllAccounts().get(vasya).get(0).getValue(), is(1000.0));
    }
}
