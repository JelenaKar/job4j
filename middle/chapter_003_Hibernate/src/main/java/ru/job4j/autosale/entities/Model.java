package ru.job4j.autosale.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "model")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "model_gen")
    @SequenceGenerator(name = "model_gen", sequenceName = "model_id_seq", allocationSize = 1)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "make_id")
    private Make make;

    public static Model of(int id) {
        Model model = new Model();
        model.id = id;
        return model;
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

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Model model = (Model) o;
        return id == model.id;
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
