package ru.job4j.set;

import org.junit.Test;

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
}