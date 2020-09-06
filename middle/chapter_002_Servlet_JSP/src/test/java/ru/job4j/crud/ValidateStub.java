package ru.job4j.crud;

import java.util.List;
import java.util.regex.Pattern;

public class ValidateStub implements Validate {
    private final Store store = MemoryStore.getInstance();
    private final Pattern pattern = Pattern.compile("^.+@.+\\..{2,3}$");

    @Override
    public CrudStatus add(User user) {
        if (this.isWrongAttributesNameLoginEmail(user)) {
            return CrudStatus.WRONG_USER_INFO;
        }
        if (!this.pattern.matcher(user.getEmail()).find()) {
            return CrudStatus.WRONG_EMAIL;
        }
        try {
            this.store.add(user);
        } catch (Exception e) {
            return CrudStatus.INSERTION_FAILED;
        }
        return CrudStatus.INSERTION_SUCCESS;
    }

    @Override
    public CrudStatus update(User user) {
        if (user == null || this.findById(user.getId()) == null) {
            return CrudStatus.WRONG_ID;
        }
        if (this.isWrongAttributesNameLoginEmail(user)) {
            return CrudStatus.WRONG_USER_INFO;
        }
        if (!this.pattern.matcher(user.getEmail()).find()) {
            return CrudStatus.WRONG_EMAIL;
        }
        try {
            this.store.update(user);
        } catch (Exception e) {
            return CrudStatus.UPDATE_FAILED;
        }
        return CrudStatus.UPDATE_SUCCESS;
    }

    @Override
    public CrudStatus delete(User user) {
        User deletedUser = this.findById(user.getId());
        if (user == null || deletedUser == null) {
            return CrudStatus.WRONG_ID;
        }
        try {
            this.store.delete(user);
        } catch (Exception e) {
            return CrudStatus.DELETE_FAILED;
        }
        return CrudStatus.DELETE_SUCCESS;
    }

    @Override
    public CrudStatus removePhoto(User user) {
        try {
            user.setPhotoid(null);
            this.store.update(user);
        } catch (Exception e) {
            return CrudStatus.PHOTO_REMOVE_FAILED;
        }
        return CrudStatus.PHOTO_REMOVE_SUCCESS;
    }

    @Override
    public User findById(long id) {
        try {
            return this.store.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User findByLogin(String login) {
        try {
            return this.store.findByLogin(login);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            return this.store.findByEmail(email);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        try {
            return this.store.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    private boolean isWrongAttributesNameLoginEmail(User user) {
        return  (user.getEmail() == null || user.getEmail().trim().isEmpty()
                || user.getName() == null || user.getName().trim().isEmpty()
                || user.getLogin() == null || user.getLogin().trim().isEmpty());
    }
}
