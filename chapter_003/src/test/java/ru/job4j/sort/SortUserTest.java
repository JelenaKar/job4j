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
        List<User> users = List.of(
                new User("Vasya", 31), new User("Petya", 59),
                new User("Igor", 29), new User("Roman", 64),
                new User("Kolya", 59));
        Set<User> expected = Set.of(
                new User("Igor", 29), new User("Vasya", 31), new User("Kolya", 59),
                new User("Petya", 59),
                new User("Roman", 64));
        SortUser sorting = new SortUser();
        Set<User> result = sorting.sort(users);
        assertThat(result, is(expected));
    }

    /**
     * Проверка сортировки пользователей по длине имени.
     */
    @Test
    public void whenSortByNameLength() {
        List<User> users = new ArrayList<>(List.of(
                new User("Василий", 31), new User("Федот", 59),
                new User("Ли", 29), new User("Иван", 64))
        );
        List<User> expected = List.of(
                new User("Ли", 29),
                new User("Иван", 64),
                new User("Федот", 59),
                new User("Василий", 31));
        SortUser sorting = new SortUser();
        List<User> result = sorting.sortNameLength(users);
        assertThat(result, is(expected));
    }

    /**
     * Проверка сортировки пользователей по имени, а затем по возрасту.
     */
    @Test
    public void whenSortByAllFields() {
        List<User> users = new ArrayList<>(List.of(
                new User("Сергей", 25), new User("Иван", 30),
                new User("Сергей", 20), new User("Иван", 25))
        );
        List<User> expected = List.of(
                new User("Иван", 25),
                new User("Иван", 30),
                new User("Сергей", 20),
                new User("Сергей", 25));
        SortUser sorting = new SortUser();
        List<User> result = sorting.sortByAllFields(users);
        assertThat(result, is(expected));
    }
}
