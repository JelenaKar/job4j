package ru.job4j.exam;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class DependenciesTest {

    /**
     * Тест подгрузки всех зависимостей скрипта №1.
     */
    @Test
    public void whenLoadScriptThenReturnAllDependencies() {
        Map<Integer, List<Integer>> ds = Map.of(1, List.of(2, 3), 2, List.of(4),
                3, List.of(4, 5), 4, new ArrayList<>(), 5, new ArrayList<>());
        List<Integer> expected = List.of(1, 2, 3, 4, 5);
        assertThat(Dependencies.load(ds, 1), is(expected));
    }

}