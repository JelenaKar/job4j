package ru.job4j.exam;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ArraysCompareTest {

    @Test
    public void whenSameCharsThenTrue() {
        Character[] arr1 = {'м', 'а', 'м', 'а'};
        Character[] arr2 = {'а', 'м', 'а', 'м'};
        ArraysCompare<Character> ac = new ArraysCompare<>();
        assertThat(ac.compare(arr1, arr2), is(true));
    }

    @Test
    public void whenDifferentCharsThenFalse() {
        Character[] arr1 = {'м', 'а', 'м', 'а'};
        Character[] arr2 = {'а', 'п', 'а', 'м'};
        ArraysCompare<Character> ac = new ArraysCompare<>();
        assertThat(ac.compare(arr1, arr2), is(false));
    }

    @Test
    public void whenDifferentLengthThenFalse() {
        Character[] arr1 = {'м', 'а', 'м', 'а'};
        Character[] arr2 = {'а', 'м', 'а', 'м', 'б'};
        ArraysCompare<Character> ac = new ArraysCompare<>();
        assertThat(ac.compare(arr1, arr2), is(false));
    }

    @Test
    public void whenSameDigitsThenTrue() {
        Integer[] arr1 = {15, -100, 0, 17, 22};
        Integer[] arr2 = {0, 22, -100, 15, 17};
        ArraysCompare<Integer> ai = new ArraysCompare<>();
        assertThat(ai.compare(arr1, arr2), is(true));
    }

    @Test
    public void whenDifferentDigitsThenFalse() {
        Integer[] arr1 = {15, -100, 0, 17, 22};
        Integer[] arr2 = {0, 22, -10, 15, 17};
        ArraysCompare<Integer> ai = new ArraysCompare<>();
        assertThat(ai.compare(arr1, arr2), is(false));
    }

}