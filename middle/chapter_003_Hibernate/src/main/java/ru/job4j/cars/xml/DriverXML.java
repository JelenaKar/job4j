package ru.job4j.cars.xml;

import java.util.List;
import java.util.Objects;

public class DriverXML {
    private int id;
    private String name;
    private List<CarXML> cars;

    public DriverXML() {
    }

    public static DriverXML of(String name) {
        DriverXML driver = new DriverXML();
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

    public List<CarXML> getCars() {
        return cars;
    }

    public void setCars(List<CarXML> cars) {
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
        DriverXML driver = (DriverXML) o;
        return id == driver.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
