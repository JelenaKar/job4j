package ru.job4j.tracker;

import java.util.List;

public class ValidateInput implements Input {

    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }

    /**
     * Выводит на экран вопрос, принимает введённый ответ.
     * @param question выводимый на экран вопрос.
     * @return введённый пользователем ответ.
     */
    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    /**
     * Выводит на экран вопрос, принимает введённый ответ с предварительной валидацией данных.
     * @param ask выводимый на экран вопрос.
     * @param range диапазон возможных пунктов меню.
     * @return введённый пользователем ответ, проверенный на корректность.
     */
    @Override
    public int ask(String ask, List<Integer> range) {
        boolean invalid = true;
        int key = -1;
        do {
            try {
                key = this.input.ask(ask, range);
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