package ru.job4j.crud;

import java.util.List;

public interface Store {
    void add(User user) throws Exception;
    void update(User user) throws Exception;
    void delete(User user) throws Exception;
    List<User> findAll() throws Exception;
    User findById(long id) throws Exception;
}
