package ru.job4j.cars.annotations;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_gen")
    @SequenceGenerator(name = "car_gen", sequenceName = "car_id_seq", allocationSize = 1)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name = "history_owner",
            joinColumns = @JoinColumn (name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id"))
    private List<Driver> drivers;

    public Car() {
    }

    public static Car of(Engine engine) {
        Car car = new Car();
        car.engine = engine;
        return car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engineId) {
        this.engine = engineId;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", engine=" + engine.getId()
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
