package ru.job4j.threadsafe;

import java.util.Objects;

/**
 * Класс-аккаунт пользователя.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */

public class User {
    private final int id;

    private double amount;

    public User(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getId() {
        return this.id;
    }

    public double getAmount() {
        return this.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
