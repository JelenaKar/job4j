package ru.job4j.bank;

import java.util.*;
import java.util.stream.Collectors;

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
        this.userAccounts.entrySet()
                .stream()
                .filter(user -> user.getKey().getPassport().equals(passport))
                .findAny()
                .map(users -> users.getValue().add(account));
    }

    /**
     * Закрывает счёт по паспортным данным.
     * @param passport паспортные данные Клиента.
     * @param account объект Счёта в банке.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        this.userAccounts.entrySet()
                .stream()
                .filter(user -> user.getKey().getPassport().equals(passport))
                .forEach(users -> users.getValue().remove(account));
    }

    /**
     * Ищет клиента по паспортным данным.
     * @param passport паспортные данные Клиента.
     * @return объект клиента.
     */
    private User getUserByPassport(String passport) {
       return this.userAccounts.entrySet()
                .stream()
                .filter(user -> user.getKey().getPassport().equals(passport))
                .findAny().map(Map.Entry::getKey).orElse(null);
    }

    /**
     * Возвращает все счета клиента по паспортным данным.
     * @param passport паспортные данные Клиента.
     * @return список всех счетов, открытых у клиента в банке.
     */
    public List<Account> getUserAccounts(String passport) {
        User user = this.getUserByPassport(passport);
        return (user != null) ? this.userAccounts.get(user) : null;
    }

    /**
     * Возвращает счёт клиента по паспортным данным и реквизитам.
     * @param passport паспортные данные Клиента.
     * @param requisite реквизиты счёта.
     * @return счёт клиента.
     */
    public Account getAccountByRequisiteFromUserPassport(String passport, String requisite) {
        return this.userAccounts.entrySet()
                .stream()
                .filter(user -> user.getKey().getPassport().equals(passport))
                .findAny().map(u -> this.userAccounts.get(u.getKey()))
                .map(u -> u.stream()
                        .filter(account -> account.getRequisites().equals(requisite))
                        .findFirst().orElse(null))
                .orElse(null);
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
