package ru.job4j.autosale.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "engine_type")
public class EngineType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "engine_gen")
    @SequenceGenerator(name = "engine_gen", sequenceName = "engine_type_id_seq", allocationSize = 1)
    private int id;

    private String name;

    public static EngineType of(int id) {
        EngineType type = new EngineType();
        type.id = id;
        return type;
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
        EngineType that = (EngineType) o;
        return id == that.id;
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
