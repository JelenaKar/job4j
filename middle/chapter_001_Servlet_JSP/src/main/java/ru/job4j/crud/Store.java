package ru.job4j.crud;

import java.util.List;

public interface Store {
    void add(User user);
    void update(User user);
    void delete(User user);
    List<User> findAll();
    User findById(long id);
}
