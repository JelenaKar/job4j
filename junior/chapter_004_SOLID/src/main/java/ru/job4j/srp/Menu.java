package ru.job4j.srp;

import java.util.function.Function;

/**
 * TODO Classname.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Menu {

    private String name;
    private Function<Double[], Void> action;
    private int amount;

    public Menu(String name, Function<Double[], Void> action, int amount) {
        this.name = name;
        this.action = action;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Function<Double[], Void> getAction() {
        return action;
    }

    public int getAmount() {
        return amount;
    }
}
