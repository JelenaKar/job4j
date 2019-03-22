package ru.job4j.map;

import java.util.Calendar;

public class User {
    private final String name;
    private final Calendar birthday;
    private int children;

    public int babyBirths() {
        return ++children;
    }

    public User(String name, Calendar birthday, int children) {
        this.name = name;
        this.birthday = birthday;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public int getChildren() {
        return children;
    }
}