package ru.job4j.sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class DepartmentSortTest {

    /**
     * Проверка сортировки подразделений по возрастанию.
     */
    @Test
    public void whenSortedDepartmentsAsc() {
        String[] departments = new String[]{"K2", "K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        DepartmentSort sorting = new DepartmentSort();
        List<String> result = sorting.sortDepartmentAsc(departments);
        List<String> expected = new ArrayList<>(Arrays.asList("K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2", "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"));
        assertThat(result, is(expected));
    }

    /**
     * Проверка сортировки подразделений по убыванию.
     */
    @Test
    public void whenSortedDepartmentsDesc() {
        String[] departments = new String[]{"K2", "K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        DepartmentSort sorting = new DepartmentSort();
        List<String> result = sorting.sortDepartmentDesc(departments);
        List<String> expected = new ArrayList<>(Arrays.asList("K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1", "K1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"));
        assertThat(result, is(expected));
    }
}
