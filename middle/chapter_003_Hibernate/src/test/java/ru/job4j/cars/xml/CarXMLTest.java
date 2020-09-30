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

public class CarXMLTest {
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
    public void whenAddCarThenInsertInDBAndWhenRemoveThenDeleteFromDb() {
        int amount = dao.findAll(CarXML.class, sf).size();
        CarXML added = dao.add(CarXML.of(EngineXML.of(3)), sf);
        assertEquals(dao.findAll(CarXML.class, sf).size(), amount + 1);
        assertEquals(dao.findById(added.getId(), CarXML.class, sf), added);
        dao.delete(added, sf);
        assertEquals(dao.findAll(CarXML.class, sf).size(), amount);
        assertNull(dao.findById(added.getId(), CarXML.class, sf));
    }

    @Test
    public void whenUpdateDriverListForCarThenAddOrRemoveCascadecarListForDriver() {
        CarXML car4 = dao.findById(4, CarXML.class, sf);
        List<DriverXML> driversList = car4.getDrivers();
        assertEquals(2, driversList.size());

        DriverXML added = dao.findById(2, DriverXML.class, sf);
        driversList.add(added);
        dao.update(car4, sf);
        car4 = dao.findById(4, CarXML.class, sf);
        assertEquals(3, car4.getDrivers().size());
        assertTrue(dao.findById(2, DriverXML.class, sf).getCars().contains(car4));

        driversList = car4.getDrivers();
        driversList.remove(added);
        dao.update(car4, sf);
        car4 = dao.findById(4, CarXML.class, sf);
        assertEquals(2, car4.getDrivers().size());
        assertFalse(dao.findById(2, DriverXML.class, sf).getCars().contains(car4));
    }

    @Test
    public void whenRemoveCarThenRemoveCascadeItFromDriversHistory() {
        CarXML car1 = dao.findById(1, CarXML.class, sf);
        assertEquals(2, car1.getDrivers().size());
        assertTrue(dao.findById(2, DriverXML.class, sf).getCars().contains(car1));
        assertTrue(dao.findById(3, DriverXML.class, sf).getCars().contains(car1));

        dao.delete(car1, sf);
        assertNull(dao.findById(1, CarXML.class, sf));
        assertFalse(dao.findById(2, DriverXML.class, sf).getCars().contains(car1));
        assertFalse(dao.findById(3, DriverXML.class, sf).getCars().contains(car1));
    }

}