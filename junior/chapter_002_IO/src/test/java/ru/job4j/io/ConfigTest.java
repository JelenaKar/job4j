package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigTest {

    private Config conf;

    @Before
    public void setUp() {
        conf = new Config(System.getProperty("user.dir") + "/src/main/resources/app.properties");
        conf.load();
    }

    @Test
    public void whenConfigKeyExists() {
        assertThat(conf.value("hibernate.connection.password"), is("password"));
    }

    @Test
    public void whenConfigKeyNotExists() {
        assertThat(conf.value("password"), is("unknown key"));
    }

}