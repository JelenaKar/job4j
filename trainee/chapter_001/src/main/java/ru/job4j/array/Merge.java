package ru.job4j.array;

/**
 * Класс массива, полученного объединением из двух.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Merge {

    /**
     * Создаёт сортированный массив из двух сортированных по возрастанию массивов.
     * @param arr1 первый отсортированный по возрастанию массив.
     * @param arr2 второй отсортированный по возрастанию массив.
     * @return объединённый отсортированный по возрастанию массив.
     */
    public int[] merge(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int m = arr2.length;
        int[] arr3 = new int[n + m];
        int i = 0, j = 0, k = 0;
        while ((i < n) && (j < m)) {
            arr3[k++] = (arr1[i] < arr2[j]) ? arr1[i++] : arr2[j++];
        }
        while (i < n) {
            arr3[k++] = arr1[i++];
        }
        while (j < m) {
            arr3[k++] = arr2[j++];
        }
        return arr3;
    }

}
