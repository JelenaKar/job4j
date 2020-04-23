package ru.job4j.crud;

import org.apache.commons.dbcp2.BasicDataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Объект хранилища в базе данных.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class DbStore implements Store {

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    private DbStore() {
        Properties config = new Properties();
        try (InputStream in = DbStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
            SOURCE.setUrl(config.getProperty("url"));
            SOURCE.setUsername(config.getProperty("username"));
            SOURCE.setPassword(config.getProperty("password"));
            SOURCE.setMinIdle(5);
            SOURCE.setMaxIdle(10);
            SOURCE.setMaxOpenPreparedStatements(100);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static DbStore getInstance() {
       return INSTANCE;
    }

    /**
     * Добавляет пользователя в базу данных.
     * @param user - объект пользователя.
     */
    @Override
    public void add(User user) throws SQLException {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO userstore (name, login, email, created, photoid) VALUES (?, ?, ?, ?, ?)")) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setLong(4, user.getCreateDate());
            st.setString(5, user.getPhotoid());
            st.executeUpdate();
        }
    }

    /**
     * Обновляет данные пользователя в базе данных.
     * @param user - объект пользователя.
     */
    @Override
    public void update(User user) throws SQLException {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("UPDATE userstore SET name = ?, login = ?, email = ?, photoid = ? WHERE id = ?")) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPhotoid());
            st.setLong(5, user.getId());
            st.executeUpdate();
        }
    }

    /**
     * Удаляет пользователя из базы данных.
     * @param user - объект пользователя.
     */
    @Override
    public void delete(User user) throws SQLException {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("DELETE FROM userstore WHERE id = ?")) {
            st.setLong(1, user.getId());
            st.executeUpdate();
        }
    }

    /**
     * Возвращает список всех пользователей из БД.
     * @return список всех пользователей.
     */
    @Override
    public List<User> findAll() throws SQLException {
        List<User> res = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection(); Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT * FROM userstore ORDER BY id")) {
            while (rs.next()) {
                res.add(this.userFromDB(rs));
            }
        }
        return res;
    }

    /**
     * Возвращает пользователя по его id в БД.
     * @param id - id пользователя.
     * @return объект пользователя.
     */
    @Override
    public User findById(long id) throws SQLException {
        User res = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM userstore WHERE id = ?")) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                res = this.userFromDB(rs);
            }
        }
        return res;
    }

    private User userFromDB(ResultSet rs) throws SQLException {
        User res = new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("login"),
                rs.getString("email"),
                rs.getString("photoid")
        );
        res.setCreateDate(rs.getLong("created"));
        return res;
    }
}
