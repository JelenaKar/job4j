package ru.job4j.tictactoe;

/**
 * Класс, эмулирующий ответы пользователя.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class StubInput implements SimpleInput {
    /**
     * Это поле содержит последовательность ответов пользователя.
     * Например {"3", "3", "y", ...}.
     * Пользователь выбирает: стандартное поле 3х3, победную комбинацию из 3 сочетаний
     * и игру крестиками, затем начинается игра.
     */
    private final String[] value;

    /**
     * Поле считает количество вызовом метода ask.
     * При каждом вызове указатель передвигается на одно число.
     */
    private int position;

    public StubInput(final String[] value) {
        this.value = value;
    }

    /**
     * Эмулятор простейший ответ пользователя.
     * @param question вопрос.
     * @return Возавращает очередное значение в виде строки из заранее заготовленного массива ответов пользователя.
     */
    @Override
    public String ask(String question) {
        System.out.print(question);
        return this.value[this.position++];
    }

    /**
     * Эмулятор простейший ответ пользователя.
     * @param question вопрос.
     * @param min - минимально возможное значение.
     * @param max - максимально возможное значение.
     * @return Возавращает очередное значение в виде целого числа
     * из заранее заготовленного массива ответов пользователя.
     * Если значение не является целым или если выходит из диапазона, возвращается -1.
     */
    @Override
    public int askNumber(String question, int min, int max) {
        System.out.print(question);
        int res = -1;
        try {
            res = Integer.valueOf(this.value[this.position++]);
            if ((res < min) || (res > max)) {
                System.out.printf("Значение должно быть в диапазоне от %d до %d%s",
                        min, max, System.lineSeparator());
                res = -1;
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Значение не является корректным целым числом!");
        }
        return res;
    }
}