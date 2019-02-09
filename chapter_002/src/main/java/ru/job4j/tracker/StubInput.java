package ru.job4j.tracker;

/**
 * Класс, эмулирующий ответы пользователя.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class StubInput implements Input {
    /**
     * Это поле содержит последовательность ответов пользователя.
     * Например {"0", "name", "description", "comment", "6"}.
     * Пользователь выбирает: добавление новой заявки с именем
     * "name", описанием "description", комментарием "comment"
     * и выходит из трекера.
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
     * Эмулятор поведения пользователя.
     * @param question вопрос.
     * @return Возавращает очередное значение из заранее заготовленного массива ответов пользователя.
     */
    @Override
    public String ask(String question) {
        return this.value[this.position++];
    }
}