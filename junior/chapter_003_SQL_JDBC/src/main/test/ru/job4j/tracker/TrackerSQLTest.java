package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.util.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")


            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Тест выгрузки из пустой БД.
     */
    @Test
    public void whenFindAllWithoutDataThenReturnEmptyArrayList() throws Exception {
        Connection connection = this.init();
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(connection));
             Statement st = connection.createStatement()) {
            st.executeUpdate("DELETE FROM item");
            List<Item> expected = new ArrayList<>();
            assertThat(tracker.findAll(), is(expected));
        }
    }

    /**
     * Тест выгрузки всех строк из хранилища.
     */
    @Test
    public void whenFindAllThenReturnAllItemsWithoutNull() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            List<Item> res = tracker.findAll();

            Item tmp = res.get(0);

            assertThat(tmp.getId(), is("1560068698798654"));
            assertThat(tmp.getName(), is("test1"));
            assertThat(tmp.getDescription(), is("test description1"));
            assertThat(tmp.getCreated(), is(1559291098000L));
            assertThat(tmp.getComments(), is("test comment1"));

            tmp = res.get(1);
            assertThat(tmp.getId(), is("1539853337798467"));
            assertThat(tmp.getName(), is("test2"));
            assertThat(tmp.getDescription(), is("test description2"));
            assertThat(tmp.getCreated(), is(1539853337000L));
            assertThat(tmp.getComments(), is("test comment2"));

            tmp = res.get(2);
            assertThat(tmp.getId(), is("1539933856710231"));
            assertThat(tmp.getName(), is("test2"));
            assertThat(tmp.getDescription(), is("test description3"));
            assertThat(tmp.getCreated(), is(1539933856000L));
            assertThat(tmp.getComments(), is("test comment3"));
        }
    }

    /**
     * Тест выгрузки заявок с заданным id, когда id существует.
     */
    @Test
    public void whenFindByIdThenReturnMatchItem() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item res = tracker.findById("1539853337798467");

            assertThat(res.getName(), is("test2"));
            assertThat(res.getDescription(), is("test description2"));
            assertThat(res.getCreated(), is(1539853337000L));
            assertThat(res.getComments(), is("test comment2"));
        }
    }

    /**
     * Тест выгрузки заявок с заданным id, когда id не существует.
     */
    @Test
    public void whenNotFindByIdThenReturnNull() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item res = tracker.findById("1111111");
            Item expected = null;
            assertThat(res, is(expected));
        }
    }

    /**
     * Тест выгрузки заявок с заданным именем, когда имя существует.
     */
    @Test
    public void whenFindByNameThenReturnMatchItems() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            List<Item> res = tracker.findByName("test2");

            Item tmp = res.get(0);

            assertThat(tmp.getId(), is("1539853337798467"));
            assertThat(tmp.getName(), is("test2"));
            assertThat(tmp.getDescription(), is("test description2"));
            assertThat(tmp.getCreated(), is(1539853337000L));
            assertThat(tmp.getComments(), is("test comment2"));

            tmp = res.get(1);
            assertThat(tmp.getId(), is("1539933856710231"));
            assertThat(tmp.getName(), is("test2"));
            assertThat(tmp.getDescription(), is("test description3"));
            assertThat(tmp.getCreated(), is(1539933856000L));
            assertThat(tmp.getComments(), is("test comment3"));
        }
    }

    /**
     * Тест выгрузки заявок с заданным именем, когда имени не существует.
     */
    @Test
    public void whenNotFindByNameThenReturnNull() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            List<Item> res = tracker.findByName("test5");
            List<Item> expected = new ArrayList<>();
            assertThat(res, is(expected));
        }
    }

    /**
     * Тест добавления новой заявки в хранилище.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item("test4", "test description4", "test comment4");
            tracker.add(item);

            Item res = tracker.findById(item.getId());
            assertThat(res.getName(), is("test4"));
        }
    }

    /**
     * Тест замены заявки.
     */
    @Test
    public void whenReplaceNameThenReturnNewInfo() throws Exception {

        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item res = tracker.findById("1539853337798467");
            assertThat(res.getName(), is("test2"));
            assertThat(res.getDescription(), is("test description2"));
            assertThat(res.getComments(), is("test comment2"));

            Item item = new Item("test4", "test description4", "test comment4");
            tracker.replace("1539853337798467", item);

            res = tracker.findById("1539853337798467");
            assertThat(res.getName(), is("test4"));
            assertThat(res.getDescription(), is("test description4"));
            assertThat(res.getComments(), is("test comment4"));
        }
    }

    /**
     * Тест удаления заявки с заданным id.
     */
    @Test
    public void whenDeleteItemThenReturnWithoutIt() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item res = tracker.findById("1539853337798467");
            assertThat(res.getName(), is("test2"));
            assertThat(tracker.delete("1539853337798467"), is(true));

            res = tracker.findById("1539853337798467");
            Item expected = null;
            assertThat(res, is(expected));
        }
    }
}