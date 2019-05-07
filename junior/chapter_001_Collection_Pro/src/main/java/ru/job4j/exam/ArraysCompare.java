package ru.job4j.exam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArraysCompare<T> {
    public boolean compare(T[] array1, T[] array2) {
        Map<T, Integer> result = new HashMap<>();
        if (array1.length != array2.length) {
            return false;
        }

        Arrays.stream(array1).forEach(
                elem -> result.merge(elem, 1, (oldVal, newVal) -> oldVal + newVal)
        );

        for (T elem : array2) {
            int n = result.computeIfAbsent(elem, key -> 0);
            if (--n < 0) {
                return false;
            }
        }

        return true;
    }
}
