package ru.job4j.cars.xml;

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

public class DriverXMLTest {
    private final SessionFactory sf = new Configuration().configure("hsqldb.cfg.xml").buildSessionFactory();
    private final Actions dao = Actions.getInstance();
    private final static Connection CONNECTION = init();

    public static Connection init() {
        try (InputStream in = EngineXMLTest.class.getClassLoader().getResourceAsStream("app.properties")) {
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
                    new File(EngineXMLTest.class.getClassLoader().getResource("prepareDB.sql").getFile()),
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
                    new File(EngineXMLTest.class.getClassLoader().getResource("dropDB.sql").getFile()),
                    CONNECTION
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddDriverThenInsertInDBAndWhenRemoveThenDeleteFromDb() {
        int amount = dao.findAll(DriverXML.class, sf).size();
        DriverXML added = dao.add(DriverXML.of("Васечкин"), sf);
        assertEquals(dao.findAll(DriverXML.class, sf).size(), amount + 1);
        assertEquals(dao.findById(added.getId(), DriverXML.class, sf), added);
        dao.delete(added, sf);
        assertEquals(dao.findAll(DriverXML.class, sf).size(), amount);
        assertNull(dao.findById(added.getId(), DriverXML.class, sf));
    }

    @Test
    public void whenUpdateCarListForDriverThenAddOrRemoveCascadeDriverListForCar() {
        DriverXML driver2 = dao.findById(2, DriverXML.class, sf);
        assertEquals(2, driver2.getCars().size());
        List<CarXML> carList = driver2.getCars();
        assertEquals(2, carList.size());

        CarXML added = dao.findById(4, CarXML.class, sf);
        carList.add(added);
        dao.update(driver2, sf);
        driver2 = dao.findById(2, DriverXML.class, sf);
        assertEquals(3, driver2.getCars().size());
        assertTrue(dao.findById(4, CarXML.class, sf).getDrivers().contains(driver2));

        carList = driver2.getCars();
        carList.remove(added);
        dao.update(driver2, sf);
        driver2 = dao.findById(2, DriverXML.class, sf);
        assertEquals(2, driver2.getCars().size());
        assertFalse(dao.findById(4, CarXML.class, sf).getDrivers().contains(driver2));
    }

    @Test
    public void whenRemoveDriverThenRemoveCascadeHisFromCarsHistory() {
        DriverXML driver2 = dao.findById(2, DriverXML.class, sf);
        assertEquals(2, driver2.getCars().size());
        assertTrue(dao.findById(1, CarXML.class, sf).getDrivers().contains(driver2));
        assertTrue(dao.findById(3, CarXML.class, sf).getDrivers().contains(driver2));

        dao.delete(driver2, sf);
        assertNull(dao.findById(2, DriverXML.class, sf));
        assertFalse(dao.findById(1, CarXML.class, sf).getDrivers().contains(driver2));
        assertFalse(dao.findById(3, CarXML.class, sf).getDrivers().contains(driver2));
    }

}