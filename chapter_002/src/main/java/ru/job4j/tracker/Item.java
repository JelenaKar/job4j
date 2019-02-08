package ru.job4j.tracker;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Item {
    private String id;
    private String name;
    private String description;
    private long created;
    private String comments;

    public Item(String name, String description, String comments) {
        this.name = name;
        this.description = description;
        this.created = System.currentTimeMillis();
        this.comments = comments;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getComments() {
        return this.comments;
    }

    public long getCreated() {
        return this.created;
    }

    public String toString() {
        Date date = new Date(this.getCreated());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy H:m:s");
        return  this.getId() + " | " + this.getName() + " | " + this.getDescription() + " | "
                + this.getComments() + " | " + dateFormat.format(date);
    }
}
