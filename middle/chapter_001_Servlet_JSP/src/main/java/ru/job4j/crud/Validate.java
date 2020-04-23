package ru.job4j.crud;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public interface Validate {
    CrudStatus add(User user);
    CrudStatus update(User user);
    CrudStatus removePhoto(User user);
    CrudStatus delete(User user);
    User findById(long id);
    List<User> findAll();
    default long parseStringIntoLong(String sNumber) {
        final Logger LOG = LogManager.getLogger(Validate.class);
        long res;
        try {
            res = Long.valueOf(sNumber);
        } catch (NumberFormatException nfe) {
            LOG.warn(nfe.getMessage());
            res = 0;
        }
        return res;
    }
}
