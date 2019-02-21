package ru.job4j.sort;

import java.util.*;

/**
 * Класс-сортировщик пользователей.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SortUser {

    /**
     * Преобразует список пользователей в отсортированное множество.
     * @param users исходный список пользователей.
     * @return отсортированное множество TreeSet.
     */
    public Set<User> sort(List<User> users) {
        return new TreeSet<>(users);
    }
}
