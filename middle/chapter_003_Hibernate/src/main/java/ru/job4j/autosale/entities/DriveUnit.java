package ru.job4j.autosale.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "drive_unit")
public class DriveUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unit_gen")
    @SequenceGenerator(name = "unit_gen", sequenceName = "driveunit_id_seq", allocationSize = 1)
    private int id;

    private String name;

    public static DriveUnit of(int id) {
        DriveUnit unit = new DriveUnit();
        unit.id = id;
        return unit;
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
        DriveUnit unit = (DriveUnit) o;
        return id == unit.id;
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
