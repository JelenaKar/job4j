package ru.job4j.parsing;

import org.junit.Test;
import ru.job4j.tracker.ConnectionRollback;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.TrackerSQL;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class SqlRuParserTest {

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
     * Тест выгрузки в БД вакансий с портала.
     */
    @Test
    public void whenFindAllWithoutDataThenReturnEmptyArrayList() throws Exception {
        Connection connection = this.init();
        try (SqlRuParser parser = new SqlRuParser(ConnectionRollback.create(connection));
             Statement st = connection.createStatement()) {
            st.executeUpdate("DELETE FROM vacancy");
            Calendar cal = Calendar.getInstance();
            cal.set(cal.get(Calendar.YEAR), Calendar.JANUARY, 1, 0, 0, 0);
            parser.parse();
            try (ResultSet res = st.executeQuery("SELECT * FROM vacancy ORDER BY created ASC LIMIT 1")) {
                assertThat(res.next(), is(true));
                assertThat(res.getString("name") != null, is(true));
                assertThat(res.getString("text") != null, is(true));
                assertThat(res.getString("link") != null, is(true));
                assertThat(res.getLong("created") >= cal.getTimeInMillis(), is(true));
            }
        }
    }
}