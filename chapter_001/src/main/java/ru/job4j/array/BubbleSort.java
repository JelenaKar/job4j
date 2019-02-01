package ru.job4j.array;

/**
 * Класс сортировки пузырьком.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BubbleSort {

    /**
     * Сортирует массив методом перестановки пузырьком.
     * @param array исходный массив.
     * @return отсортированный array массив.
     */
    public  int[] sort(int[] array) {
        boolean f;
        int n = array.length;
        int temp;
        do {
            f = false;
            for (int i = 1; i < n; i++) {
                if (array[i] < array [i - 1]) {
                    temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                    f = true;
                }
            }
            n--;
        } while (f);
        return array;
    }
}