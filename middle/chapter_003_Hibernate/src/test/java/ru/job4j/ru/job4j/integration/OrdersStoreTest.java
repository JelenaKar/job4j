package ru.job4j.ru.job4j.integration;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.annotations.BeforeTest;
import ru.job4j.cars.annotations.EngineTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrdersStoreTest {
    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(OrdersStoreTest.class.getClassLoader().getResource("update_001.sql").getFile()))
            )
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @After
    public void clear() throws SQLException {
        pool.getConnection().createStatement().executeQuery("TRUNCATE TABLE orders RESTART IDENTITY");
    }

    @Test
    public void whenSaveOrderAndFindAllThenOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);
        store.save(Order.of("name1", "description1"));
        List<Order> all = (List<Order>) store.findAll();
        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    public void whenSaveOrderAndUpdateThenFindModifiedOrder() {
        OrdersStore store = new OrdersStore(pool);
        Order primary = store.save(Order.of("name1", "description1"));
        Order modified = Order.of("name2", "description2");
        modified.setId(primary.getId());
        store.update(modified);
        Order extracted = store.findById(primary.getId());
        assertThat(extracted.getDescription(), is("description2"));
        assertThat(extracted.getName(), is("name2"));
    }

    @Test
    public void whenSaveOrderAndGetByNameThenRetrieveNecessaryOrder() {
        OrdersStore store = new OrdersStore(pool);
        store.save(Order.of("name3", "description3"));
        Order extracted = store.findByName("name3");
        assertThat(extracted.getDescription(), is("description3"));
        assertThat(extracted.getName(), is("name3"));
    }
}