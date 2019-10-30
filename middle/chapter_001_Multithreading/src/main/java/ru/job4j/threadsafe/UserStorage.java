package ru.job4j.threadsafe;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс-хранилище пользовательских аккаунтов.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final Map<Integer, User> storage = new HashMap<>();

    /**
     * Добавляет пользовательский аккаунт в хранилище.
     * @param user объект пользователя.
     */
    public synchronized void add(User user) {
        storage.put(user.getId(), user);
    }

    /**
     * Обновляет значения для пользовательского аккаунта.
     * @param user объект пользователя.
     * @return true если обновления прошли успешно, false если пользователь не найден.
     */
    public synchronized boolean update(User user) {
        return storage.computeIfPresent(user.getId(), (k, v) -> user) != null;
    }

    /**
     * Удаляет пользовательский аккаунт из хранилища.
     * @param user объект пользователя.
     * @return true если пользователь успешно удалён, false в противном случае.
     */
    public synchronized boolean delete(User user) {
        return storage.remove(user.getId(), user);
    }

    /**
     * Рассчитывает общую сумму на всех счетах.
     * @return общая сумма.
     */
    public synchronized double getTotal() {
        return storage.values().stream().map(User::getAmount).mapToDouble(Double::doubleValue).sum();
    }

    public synchronized Map<Integer, User> getStorage() {
        return storage;
    }

    /**
     * Производит транзакцию между двумя счетами.
     * @param fromId - id счёта, с которого совершается перевод.
     * @param toId - id счёта, на который совершается перевод.
     * @param amount - сумма транзакции.
     * @return true если транзакция произведена успешно, false - в противном случае.
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean res = false;
        User fromAccount = storage.get(fromId);
        if (fromAccount.getAmount() >= amount) {
            User toAccount = storage.get(toId);
            fromAccount.setAmount(fromAccount.getAmount() - amount);
            toAccount.setAmount(toAccount.getAmount() + amount);
            this.update(fromAccount);
            this.update(toAccount);
            res = true;
        }
        return res;
    }

}