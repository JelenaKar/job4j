package ru.job4j.machine;

public class NotEnoughMoney extends RuntimeException {
    public NotEnoughMoney(String msg) {
        super(msg);
    }
}
