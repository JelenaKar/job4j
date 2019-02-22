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

    /**
     * Сортирует список пользователей по длине имени.
     * @param users исходный список пользователей.
     * @return отсортированный список.
     */
    public List<User> sortNameLength(List<User> users) {
        users.sort(
            new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return Integer.compare(o1.getName().length(), o2.getName().length());
                }
            });
        return users;
    }

    /**
     * Сортирует список пользователей по имени, а затем по возрасту.
     * @param users исходный список пользователей.
     * @return отсортированный список.
     */
    public List<User> sortByAllFields(List<User> users) {
        users.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        int k = o1.getName().compareTo(o2.getName());
                        return (k != 0) ? k : Integer.compare(o1.getAge(), o2.getAge());
                    }
                });
        return users;
    }
}
