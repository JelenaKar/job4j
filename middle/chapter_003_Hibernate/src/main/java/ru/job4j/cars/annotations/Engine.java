package ru.job4j.cars.annotations;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "engine")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "engine_gen")
    @SequenceGenerator(name = "engine_gen", sequenceName = "engine_id_seq", allocationSize = 1)
    private int id;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "engine", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<Car> cars;

    public static Engine of(int id) {
        Engine engine = new Engine();
        engine.id = id;
        return engine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Engine engine = (Engine) o;
        return id == engine.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
