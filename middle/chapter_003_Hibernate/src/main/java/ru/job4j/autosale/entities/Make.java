package ru.job4j.autosale.entities;

import javax.persistence.*;
import java.util.Objects;

@NamedQuery(
        name = "findAllMakes",
        query = "from ru.job4j.autosale.entities.Make"
)
@Entity(name = "make")
public class Make {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "make_gen")
    @SequenceGenerator(name = "make_gen", sequenceName = "make_id_seq", allocationSize = 1)
    private int id;
    private String name;

    public static Make of(int id) {
        Make make = new Make();
        make.id = id;
        return make;
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
        Make make = (Make) o;
        return id == make.id;
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