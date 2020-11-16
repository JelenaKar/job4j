package ru.job4j.autosale.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "auto")
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_gen")
    @SequenceGenerator(name = "auto_gen", sequenceName = "auto_id_seq", allocationSize = 1)
    private int id;

    @ManyToOne
    @JoinColumn(name = "make_id")
    private Make make;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
    private int manufactured;

    @ManyToOne
    @JoinColumn(name = "body_id")
    private BodyStyle bodyStyle;

    private int doors;

    @ManyToOne
    @JoinColumn(name = "engine_id")
    private EngineType engine;

    @ManyToOne
    @JoinColumn(name = "driveunit_id")
    private DriveUnit driveUnit;

    @ManyToOne
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    private String modification;

    @Column(name = "leftwheel")
    private boolean leftWheel;

    private int mileage;

    @Column(name = "isbroken")
    private boolean isBroken;

    private String color;

    @Column(name = "owner_number")
    private int ownerNumber;

    public Auto() {
    }

    public static Auto of(int id) {
        Auto auto = new Auto();
        auto.id = id;
        return auto;
    }

    public static Auto of(Make make, Model model, int manufactured, BodyStyle bodyStyle, int doors, EngineType engine, DriveUnit driveUnit, Transmission transmission, String modification, boolean leftWheel, int mileage, boolean isBroken, String color, int ownerNumber) {
        Auto auto = new Auto();
        auto.make = make;
        auto.model = model;
        auto.manufactured = manufactured;
        auto.bodyStyle = bodyStyle;
        auto.doors = doors;
        auto.engine = engine;
        auto.driveUnit = driveUnit;
        auto.transmission = transmission;
        auto.modification = modification;
        auto.leftWheel = leftWheel;
        auto.mileage = mileage;
        auto.isBroken = isBroken;
        auto.color = color;
        auto.ownerNumber = ownerNumber;
        return auto;
    }

    /*public static Auto of(int id, Make make, Model model, int manufactured, BodyStyle bodyStyle, int doors, EngineType engine, DriveUnit driveUnit, Transmission transmission, String modification, boolean leftWheel, int mileage, boolean isBroken, String color, int ownerNumber) {
        Auto auto = Auto.of(make, model, manufactured, bodyStyle, doors, engine, driveUnit, transmission, modification, leftWheel, mileage, isBroken, color, ownerNumber);
        auto.id = id;
        return auto;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getManufactured() {
        return manufactured;
    }

    public void setManufactured(int manufactured) {
        this.manufactured = manufactured;
    }

    public BodyStyle getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(BodyStyle bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public EngineType getEngine() {
        return engine;
    }

    public void setEngine(EngineType engine) {
        this.engine = engine;
    }

    public DriveUnit getDriveUnit() {
        return driveUnit;
    }

    public void setDriveUnit(DriveUnit driveUnit) {
        this.driveUnit = driveUnit;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }

    public boolean getLeftWheel() {
        return leftWheel;
    }

    public void setLeftWheel(boolean leftWheel) {
        this.leftWheel = leftWheel;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public boolean getisBroken() {
        return isBroken;
    }

    public void setisBroken(boolean broken) {
        isBroken = broken;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getOwnerNumber() {
        return ownerNumber;
    }

    public void setOwnerNumber(int ownerNumber) {
        this.ownerNumber = ownerNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Auto auto = (Auto) o;
        return id == auto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Auto{"
                + "id=" + id
                + ", make='" + make + '\''
                + ", model='" + model + '\''
                + ", manufactured=" + manufactured
                + ", bodyStyle=" + bodyStyle
                + ", doors=" + doors
                + ", engine=" + engine
                + ", driveUnit=" + driveUnit
                + ", transmission=" + transmission
                + ", modification='" + modification + '\''
                + ", leftWheel=" + leftWheel
                + ", mileage=" + mileage
                + ", isBroken=" + isBroken
                + ", color='" + color + '\''
                + ", ownerNumber=" + ownerNumber
                + '}';
    }
}
