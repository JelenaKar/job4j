package ru.job4j.cars.xml;

import java.util.List;
import java.util.Objects;

public class CarXML {
    private int id;
    private EngineXML engine;
    private List<DriverXML> drivers;

    public CarXML() {
    }

    public static CarXML of(EngineXML engine) {
        CarXML car = new CarXML();
        car.engine = engine;
        return car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EngineXML getEngine() {
        return engine;
    }

    public void setEngine(EngineXML engineId) {
        this.engine = engineId;
    }

    public List<DriverXML> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverXML> drivers) {
        this.drivers = drivers;
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
        CarXML car = (CarXML) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
