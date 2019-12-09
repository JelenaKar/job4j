package ru.job4j.crud;

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

    public User(String name, String login, String email) {
        this.id = 0;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = System.currentTimeMillis();
    }

    public User(long id, String name, String login, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = System.currentTimeMillis();
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
}