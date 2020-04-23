package ru.job4j.crud;

import java.io.File;
import java.util.Objects;

/**
 * Класс пользователя.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public class User {

    private long id;
    private String name;
    private String login;
    private String email;
    private long createDate;
    private String photoid;
    public static final String IMG_DIR = System.getProperty("user.home") + File.separator + "bin" + File.separator + "images";

    public User(String name, String login, String email, String photoid) {
        this.id = 0;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = System.currentTimeMillis();
        this.photoid = photoid;
    }

    public User(long id, String name, String login, String email, String photoid) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = System.currentTimeMillis();
        this.photoid = photoid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getPhotoid() {
        return photoid;
    }

    public void setPhotoid(String photoid) {
        this.photoid = photoid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return this.id == user.id && this.createDate == user.createDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.createDate);
    }
}