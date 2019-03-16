package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ArrayDuplicateTest {

    /**
     * Тест удаления дублирующихся слов из массива.
     */
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] arr = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        arr = duplicate.remove(arr);
        String[] expected = {"Привет", "Мир", "Супер"};
        assertThat(arr, is(expected));
    }
}

