package ru.job4j.cars.annotations;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.cars.Actions;
import ru.job4j.utils.FileSqlParser;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

public class CarTest {
    private final SessionFactory sf = new Configuration().configure("hsqldb.cfg.xml").buildSessionFactory();
    private final Actions dao = Actions.getInstance();
    private final static Connection CONNECTION = init();

    public static Connection init() {
        try (InputStream in = EngineTest.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @BeforeClass
    public static void createDB() {
        try {
            FileSqlParser.readSqlFile(
                    new File(EngineTest.class.getClassLoader().getResource("prepareDB.sql").getFile()),
                    CONNECTION
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void dropDB() {
        try {
            FileSqlParser.readSqlFile(
                    new File(EngineTest.class.getClassLoader().getResource("dropDB.sql").getFile()),
                    CONNECTION
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddCarThenInsertInDBAndWhenRemoveThenDeleteFromDb() {
        int amount = dao.findAll(Car.class, sf).size();
        Car added = dao.add(Car.of(Engine.of(3)), sf);
        assertEquals(dao.findAll(Car.class, sf).size(), amount + 1);
        assertEquals(dao.findById(added.getId(), Car.class, sf), added);
        dao.delete(added, sf);
        assertEquals(dao.findAll(Car.class, sf).size(), amount);
        assertNull(dao.findById(added.getId(), Car.class, sf));
    }

    @Test
    public void whenUpdateDriverListForCarThenAddOrRemoveCascadecarListForDriver() {
        Car car4 = dao.findById(4, Car.class, sf);
        List<Driver> driversList = car4.getDrivers();
        assertEquals(2, driversList.size());

        Driver added = dao.findById(2, Driver.class, sf);
        driversList.add(added);
        dao.update(car4, sf);
        car4 = dao.findById(4, Car.class, sf);
        assertEquals(3, car4.getDrivers().size());
        assertTrue(dao.findById(2, Driver.class, sf).getCars().contains(car4));

        driversList = car4.getDrivers();
        driversList.remove(added);
        dao.update(car4, sf);
        car4 = dao.findById(4, Car.class, sf);
        assertEquals(2, car4.getDrivers().size());
        assertFalse(dao.findById(2, Driver.class, sf).getCars().contains(car4));
    }

    @Test
    public void whenRemoveCarThenRemoveCascadeItFromDriversHistory() {
        Car car1 = dao.findById(1, Car.class, sf);
        assertEquals(2, car1.getDrivers().size());
        assertTrue(dao.findById(2, Driver.class, sf).getCars().contains(car1));
        assertTrue(dao.findById(3, Driver.class, sf).getCars().contains(car1));

        dao.delete(car1, sf);
        assertNull(dao.findById(1, Car.class, sf));
        assertFalse(dao.findById(2, Driver.class, sf).getCars().contains(car1));
        assertFalse(dao.findById(3, Driver.class, sf).getCars().contains(car1));
    }

}