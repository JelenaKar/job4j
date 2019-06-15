package ru.job4j.xmlconverter;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final Properties values = new Properties();

    public void init() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("sqllight.properties")) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public Config() {
        this.init();
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }
}
