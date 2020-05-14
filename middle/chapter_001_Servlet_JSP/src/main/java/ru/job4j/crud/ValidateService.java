package ru.job4j.crud;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Класс валидации и работы с хранилищем.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public class ValidateService implements Validate {
    private static final Logger LOG = LogManager.getLogger(ValidateService.class);
    private static final ValidateService INSTANCE = new ValidateService();
    private final Store store = DbStore.getInstance();
    private final Pattern pattern = Pattern.compile("^.+@.+\\..{2,3}$");

    private ValidateService() {
    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    /**
     * Добавляет пользователя в базу данных.
     * @param user - объект пользователя.
     * @return статус успешности операции.
     */
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
            LOG.error(e.getMessage());
            return CrudStatus.INSERTION_FAILED;
        }
        return CrudStatus.INSERTION_SUCCESS;
    }

    /**
     * Обновляет данные пользователя в базе данных.
     * @param user - объект пользователя.
     * @return статус успешности операции.
     */
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
            LOG.error(e.getMessage());
            return CrudStatus.UPDATE_FAILED;
        }
        return CrudStatus.UPDATE_SUCCESS;
    }

    /**
     * Удаляет пользователя из базы данных.
     * @param user - объект пользователя.
     * @return статус успешности операции.
     */
    @Override
    public CrudStatus delete(User user) {
        User deletedUser = this.findById(user.getId());
        if (user == null || deletedUser == null) {
            return CrudStatus.WRONG_ID;
        }
        try {
            this.store.delete(user);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return CrudStatus.DELETE_FAILED;
        }
        if (deletedUser.getPhotoid() != null) {
            try {
                Files.delete(Path.of(User.IMG_DIR + File.separator + deletedUser.getPhotoid()));
            } catch (IOException e) {
                LOG.error(e.getMessage());
                return CrudStatus.PHOTO_REMOVE_FAILED;
            }
        }
        return CrudStatus.DELETE_SUCCESS;
    }
    /**
     * Удаляет фото пользователя.
     * @param user - объект пользователя.
     * @return статус успешности операции.
     */
    @Override
    public CrudStatus removePhoto(User user) {
        try {
            user.setPhotoid(null);
            this.store.update(user);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return CrudStatus.PHOTO_REMOVE_FAILED;
        }
        return CrudStatus.PHOTO_REMOVE_SUCCESS;
    }

    /**
     * Возвращает пользователя по его id в БД.
     * @param id - id пользователя.
     * @return объект пользователя.
     */
    @Override
    public User findById(long id) {
        try {
            return this.store.findById(id);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
    }

    @Override
    public User findByLogin(String login) {
        try {
            return this.store.findByLogin(login);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            return this.store.findByEmail(email);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
    }

    /**
     * Возвращает список всех пользователей из БД.
     * @return список всех пользователей.
     */
    @Override
    public List<User> findAll() {
        try {
            return this.store.findAll();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
    }

    private boolean isWrongAttributesNameLoginEmail(User user) {
        return  (user.getEmail() == null || user.getEmail().trim().isEmpty()
                || user.getName() == null || user.getName().trim().isEmpty()
                || user.getLogin() == null || user.getLogin().trim().isEmpty());
    }
}
