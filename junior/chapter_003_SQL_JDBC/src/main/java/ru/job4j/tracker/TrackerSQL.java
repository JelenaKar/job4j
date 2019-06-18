package ru.job4j.tracker;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Класс - хранилище данных заявок на базе Postgresql.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class TrackerSQL implements ITracker, AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class.getName());

    private Connection connection;

    public TrackerSQL() {
        this.connection = this.init();
    }

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }

    @Override
    public Item add(Item item) {
        item.setId(this.generateId(item.getCreated()));
        Item res = null;
        try (PreparedStatement itemSt = this.connection.prepareStatement(
                "INSERT INTO item (name, description, category_id, state_id, user_id, created, uniqhash) VALUES (?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);

        ) {
            itemSt.setString(1, item.getName());
            itemSt.setString(2, item.getDescription());
            itemSt.setInt(3, 4);
            itemSt.setInt(4, 1);
            itemSt.setInt(5, 1);
            itemSt.setLong(6, item.getCreated());
            itemSt.setString(7, item.getId());
            int affectedRows = itemSt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = itemSt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int ind = rs.getInt(1);
                        try (PreparedStatement commentSt = this.connection.prepareStatement(
                                "INSERT INTO comments (comment, item_id, user_id) VALUES (?, ?, ?)")) {
                            commentSt.setString(1, item.getComments());
                            commentSt.setInt(2, ind);
                            commentSt.setInt(3, 1);
                            if (commentSt.executeUpdate() > 0) {
                                res = item;
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return res;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean res = false;
        try (PreparedStatement itemSt = this.createPreparedStatement(this.connection,
                "UPDATE item SET name = ?, description = ? WHERE item.uniqhash = ?",
                3, item.getName(), item.getDescription(), id);
             PreparedStatement commSt = this.connection.prepareStatement(
                     "UPDATE comments SET comment = ? WHERE item_id = ?");
        ) {
            if (itemSt.executeUpdate() > 0) {
                try (ResultSet updated = itemSt.getGeneratedKeys()) {
                    if (updated.next()) {
                        commSt.setString(1, item.getComments());
                        commSt.setInt(2, updated.getInt(1));
                        if (commSt.executeUpdate() > 0) {
                            res = true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return res;
    }

    @Override
    public boolean delete(String id) {
        boolean res = false;
        try (PreparedStatement st = this.createPreparedStatement(this.connection,
                "DELETE FROM item WHERE item.uniqhash = ?", 1, id)) {
            if (st.executeUpdate() > 0) {
                res = true;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return res;
    }

    @Override
    public List<Item> findAll() {
        List<Item> res = new ArrayList<>();
        try (Statement st = this.connection.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT i.*, c.comment FROM item AS i LEFT OUTER JOIN comments AS c ON i.id = c.item_id")) {
            while (rs.next()) {
                res.add(this.itemFromDB(rs));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return res;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> res = new ArrayList<>();
        try (PreparedStatement st = this.createPreparedStatement(this.connection,
                "SELECT i.*, c.comment FROM item AS i LEFT OUTER JOIN comments AS c ON i.id = c.item_id WHERE i.name = ?",
                1, key);
             ResultSet rs = st.executeQuery();
        ) {
            while (rs.next()) {
                res.add(this.itemFromDB(rs));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return res;
    }

    @Override
    public Item findById(String id) {
        Item res = null;
        try (PreparedStatement st = this.createPreparedStatement(this.connection,
                "SELECT i.*, c.comment FROM item AS i LEFT OUTER JOIN comments AS c ON i.id = c.item_id WHERE i.uniqhash = ?",
                1, id);
             ResultSet rs = st.executeQuery();
        ) {
            if (rs.next()) {
                res = this.itemFromDB(rs);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return res;
    }

    private Item itemFromDB(ResultSet rs) throws SQLException {
        Item res = new Item(
                rs.getString("name"),
                rs.getString("description"),
                rs.getString("comment"),
                rs.getLong("created")
        );
        res.setId(rs.getString("uniqhash"));
        return res;
    }

    public Connection getConnection() {
        return this.connection;
    }

    private PreparedStatement createPreparedStatement(Connection con, String sql, int nmb, String ... key) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        for (int i = 1; i <= nmb; i++) {
            ps.setString(i, key[i - 1]);
        }
        return ps;
    }

    public static void main(String[] args) {
        try (TrackerSQL sql = new TrackerSQL()) {
            new StartUI(new ValidateInput(new ConsoleInput()), sql, System.out::println).init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}