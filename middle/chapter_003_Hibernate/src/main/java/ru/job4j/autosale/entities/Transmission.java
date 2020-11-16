package ru.job4j.autosale.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "transmission")
public class Transmission {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transmission_gen")
    @SequenceGenerator(name = "transmission_gen", sequenceName = "transmission_id_seq", allocationSize = 1)
    private int id;

    private String name;

    public static Transmission of(int id) {
        Transmission transmission = new Transmission();
        transmission.id = id;
        return transmission;
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
        Transmission that = (Transmission) o;
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
