package ru.job4j.tracker;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Objects;

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

    public Item(String name, String description, String comments, long created) {
        this.name = name;
        this.description = description;
        this.created = created;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return created == item.created && id.equals(item.id)
                && name.equals(item.name) && description.equals(item.description)
                && Objects.equals(comments, item.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, created, comments);
    }
}
