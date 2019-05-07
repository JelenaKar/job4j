package ru.job4j.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                .filter(str -> !str.isEmpty() && str.charAt(0) != '#')
                .forEach(
                        str -> {
                            String[] vals = str.split("=");
                            values.put(vals[0], vals[1]);
                        }
                );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.computeIfAbsent(key, x -> "unknown key");
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
