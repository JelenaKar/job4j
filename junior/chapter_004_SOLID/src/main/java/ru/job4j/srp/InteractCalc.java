package ru.job4j.srp;

import ru.job4j.calculator.Calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Интерактивный калькулятор - оболочка над классом Calculator.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class InteractCalc extends Calculator {

    protected List<Action> operations;

    public InteractCalc() {
        this.operations = new ArrayList<>(List.of(
                new Action("Сложение", x -> {
                    this.add(x[0], x[1]);
                    return null;
                }, 2),
                new Action("Вычитание", x -> {
                    this.subtract(x[0], x[1]);
                    return null;
                }, 2),
                new Action("Умножение", x -> {
                    this.multiple(x[0], x[1]);
                    return null;
                }, 2),
                new Action("Деление", x -> {
                    this.div(x[0], x[1]);
                    return null;
                }, 2)
        ));
    }

    public List<Action> getOperations() {
        return this.operations;
    }



}
