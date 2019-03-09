package ru.job4j.map;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс-преобразователь пользователя.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserConvert {

    /**
     * Преобразовать список пользователей в карту.
     * @param list исходный список пользователей.
     * @return карта пользователей.
     */
    public HashMap<Integer, User> process(List<User> list) {
        return (HashMap<Integer, User>) list.stream()
                .collect(Collectors.toMap(User::getId, user -> user));
    }
}
