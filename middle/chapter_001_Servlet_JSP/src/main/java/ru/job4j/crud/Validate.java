package ru.job4j.crud;

import java.util.List;

public interface Validate {
    CrudStatus add(User user);
    CrudStatus update(User user);
    CrudStatus delete(User user);
    User findById(long id);
    List<User> findAll();
    default long parseStringIntoLong(String sNumber) {
        long res;
        try {
            res = Long.valueOf(sNumber);
        } catch (NumberFormatException nfe) {
            res = 0;
        }
        return res;
    }
}
