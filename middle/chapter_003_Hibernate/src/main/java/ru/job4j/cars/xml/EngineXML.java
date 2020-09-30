package ru.job4j.cars.xml;

import java.util.List;
import java.util.Objects;

public class EngineXML {

    private int id;

    private List<CarXML> cars;

    public static EngineXML of(int id) {
        EngineXML engine = new EngineXML();
        engine.id = id;
        return engine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CarXML> getCars() {
        return cars;
    }

    public void setCars(List<CarXML> cars) {
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
        EngineXML engine = (EngineXML) o;
        return id == engine.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
