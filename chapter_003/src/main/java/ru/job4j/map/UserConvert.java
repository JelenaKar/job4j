package ru.job4j.map;

import java.util.HashMap;
import java.util.List;

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
        HashMap<Integer, User> users = new HashMap<>(list.size());

        for (User user : list) {
            users.put(user.getId(), user);
        }

        return users;
    }
}
