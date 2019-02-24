package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс банк, реализующий все операции с клиентами и их счетами.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Bank {
    private Map<User, List<Account>> userAccounts = new HashMap<>();

    /**
     * Добавляет нового клиента в базу.
     * @param user объект Клиента.
     */
    public void addUser(User user) {
        this.userAccounts.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Удаляет клиента из базы.
     * @param user объект Клиента.
     */
    public void deleteUser(User user) {
        this.userAccounts.remove(user);
    }

    /**
     * Открывает новый счёт по паспортным данным.
     * @param passport паспортные данные Клиента.
     * @param account объект Счёта в банке.
     */
    public void addAccountToUser(String passport, Account account) {
        for (Map.Entry<User, List<Account>> users : this.userAccounts.entrySet()) {
            if (users.getKey().getPassport().equals(passport)) {
                users.getValue().add(account);
                break;
            }
        }
    }

    /**
     * Закрывает счёт по паспортным данным.
     * @param passport паспортные данные Клиента.
     * @param account объект Счёта в банке.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        for (Map.Entry<User, List<Account>> users : this.userAccounts.entrySet()) {
            if (users.getKey().getPassport().equals(passport)) {
                users.getValue().remove(account);
                break;
            }
        }
    }

    /**
     * Ищет клиента по паспортным данным.
     * @param passport паспортные данные Клиента.
     * @return объект клиента.
     */
    public User getUserByPassport(String passport) {
        User searchedUser = null;
        for (Map.Entry<User, List<Account>> users : this.userAccounts.entrySet()) {
            if (users.getKey().getPassport().equals(passport)) {
                searchedUser = users.getKey();
                break;
            }
        }
        return searchedUser;
    }

    /**
     * Возвращает все счета клиента по паспортным данным.
     * @param passport паспортные данные Клиента.
     * @return список всех счетов, открытых у клиента в банке.
     */
    public List<Account> getUserAccounts(String passport) {
        User user = this.getUserByPassport(passport);
        return this.userAccounts.get(user);
    }

    /**
     * Возвращает счёт клиента по паспортным данным и реквизитам.
     * @param passport паспортные данные Клиента.
     * @param requisite реквизиты счёта.
     * @return счёт клиента.
     */
    Account getAccountByRequisiteFromUserPassport(String passport, String requisite) {
        List<Account> userAccounts = this.getUserAccounts(passport);
        Account account = new Account(0, "");
        for (Account elem : userAccounts) {
            if (elem.getRequisites().equals(requisite)) {
                account = elem;
                break;
            }
        }

        return account;
    }

    /**
     * Переводит средства с одного счёта на другой по паспортным данным.
     * @param srcPassport паспортные данные отправителя.
     * @param srcRequisite реквизиты отправителя.
     * @param destPassport паспортные данные получателя.
     * @param destRequisite реквизиты получателя.
     * @return true, если операция прошла успешна, и false в противном случае.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                  String destPassport, String destRequisite, double amount) {

        Account sender = this.getAccountByRequisiteFromUserPassport(srcPassport, srcRequisite);
        Account receiver = this.getAccountByRequisiteFromUserPassport(destPassport, destRequisite);

        return (sender != null) && sender.transfer(receiver, amount);
    }

    public Map<User, List<Account>> getAllAccounts() {
        return this.userAccounts;
    }
}
