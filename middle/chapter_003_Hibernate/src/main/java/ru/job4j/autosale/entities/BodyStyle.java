package ru.job4j.autosale.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "bodystyle")
public class BodyStyle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "body_gen")
    @SequenceGenerator(name = "body_gen", sequenceName = "body_id_seq", allocationSize = 1)
    private int id;
    private String name;

    public static BodyStyle of(int id) {
        BodyStyle style = new BodyStyle();
        style.id = id;
        return style;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BodyStyle bodyStyle = (BodyStyle) o;
        return id == bodyStyle.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name;
    }
}
