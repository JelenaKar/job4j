package ru.job4j.cars.annotations;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver_gen")
    @SequenceGenerator(name = "driver_gen", sequenceName = "driver_id_seq", allocationSize = 1)
    private int id;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name = "history_owner",
            joinColumns = @JoinColumn (name = "driver_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private List<Car> cars;

    public Driver() {
    }

    public static Driver of(String name) {
        Driver driver = new Driver();
        driver.name = name;
        return driver;
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

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Driver{"
                + "id=" + id
                + ", name='" + name + '\''
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
        Driver driver = (Driver) o;
        return id == driver.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
