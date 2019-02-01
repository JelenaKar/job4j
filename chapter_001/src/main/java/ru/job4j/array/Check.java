package ru.job4j.array;

/**
 * Класс, проверяющий булев массив на однородность.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Check {

    /**
     * Метод проверки массива на однородность.
     * @param data исходный булев массив.
     * @return true в случае однородности и false в случае неоднородности массива.
     */
    public boolean mono(boolean[] data) {
        boolean result = true, first = data[0];
        for (boolean elem : data) {
            if (elem != first) {
                result = false;
                break;
            }
        }
        return result;
    }
}
