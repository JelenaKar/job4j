package ru.job4j.tracker;

import java.util.List;

public class ValidateInput extends ConsoleInput {
    @Override
    public int ask(String ask, List<Integer> range) {
        boolean invalid = true;
        int key = -1;
        do {
            try {
                key = super.ask(ask, range);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Введите валидное целое число для выбора пункта меню!");
            } catch (MenuOutException moe) {
                System.out.println("Введите число из диапазона пунктов меню!");
            }
        } while (invalid);
        return key;
    }
}
