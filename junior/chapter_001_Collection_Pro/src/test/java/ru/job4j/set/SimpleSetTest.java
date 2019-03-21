package ru.job4j.set;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleSetTest {

    /**
     * Тестирование вставки дублирующихся элементов и работы итератора.
     */
    @Test
    public void whenAddDuplicateElementThenIgnoreIt() {

        SimpleSet<String> set = new SimpleSet<>();
        set.add("привет");
        set.add("всем");
        set.add("привет");
        int cnt = 0;
        for (String elem : set) {
            if ("привет".equals(elem)) {
                cnt++;
            }
        }

        assertThat(cnt, is(1));
    }

    /**
     * Тестирование вставки дублирующихся элементов и работы итератора.
     */
    @Test
    public void whenAddDuplicateNullElementsThenIgnoreOne() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(null);
        set.add(5);
        set.add(null);
        SimpleSet<Integer> expect = new SimpleSet<>();
        expect.add(1);
        expect.add(null);
        expect.add(5);
        assertThat(set, is(expect));
    }
}