package ru.job4j.array;

/**
 * Класс - обёртка над строкой.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ArrayChar {
    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет. что слово начинается с префикса.
     * @param prefix префикс.
     * @return если слово начинается с префикса.
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] chars = prefix.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != this.data[i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}