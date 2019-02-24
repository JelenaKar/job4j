package ru.job4j.bank;

/**
 * Класс счёта в банке.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Account {
    private double value;
    private String requisites;

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean transfer(Account receiver, double amount) {
        boolean success = false;
        if (receiver != null && amount > 0 && amount <= this.value) {
            this.value -= amount;
            receiver.value += amount;
            success = true;
        }
        return success;
    }
}
