package ru.job4j.sort;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.*;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SortUserTest {

    /**
     * Проверка сортировки пользователей по возрасту и имени.
     */
    @Test
    public void whenCreateSortedTreeSetFromList() {
        List<User> users = new ArrayList<>(Arrays.asList(
                new User("Vasya", 31), new User("Petya", 59),
                new User("Igor", 29), new User("Roman", 64),
                new User("Kolya", 59)));
        Set<User> expected = new TreeSet<>(Arrays.asList(
                new User("Igor", 29), new User("Vasya", 31), new User("Kolya", 59),
                new User("Petya", 59),
                new User("Roman", 64)));
        SortUser sorting = new SortUser();
        Set<User> result = sorting.sort(users);
        assertThat(result, is(expected));
    }
}
