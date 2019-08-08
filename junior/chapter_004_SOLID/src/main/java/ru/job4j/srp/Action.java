package ru.job4j.srp;

import java.util.function.Function;

/**
 * Класс - пункт меню.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Action {

    private String name;
    private Function<Double[], Void> action;
    private int amount;

    public Action(String name, Function<Double[], Void> action, int amount) {
        this.name = name;
        this.action = action;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Function<Double[], Void> getFunction() {
        return action;
    }

    public int getAmount() {
        return amount;
    }

}
